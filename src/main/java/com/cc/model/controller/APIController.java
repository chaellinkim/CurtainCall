package com.cc.model.controller;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONObject;
import org.json.XML;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.cc.model.dto.PlaceDto;
import com.cc.model.dto.PlayDto;
import com.cc.model.service.PlaceService;
import com.cc.model.service.PlayService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class APIController {
	
    private PlayService playService;
	private PlaceService placeService;
	
	@Autowired
	public APIController(PlayService playService, PlaceService placeService) {
		super();
		this.playService = playService;
		this.placeService = placeService;
	}



	@RequestMapping("/api") 
	public String playList(Model model) {
        String url = "http://www.kopis.or.kr/openApi/restful/pblprfr";
        String url2 = "http://www.kopis.or.kr/openApi/restful/prfplc";
        String serviceKey = "722233b68ffa4883ae5213ccf16565a5";
        String stDate = "20200101";
        String edDate = "20230630";
        String rows = "100";
        String cPage = "1";

        String apiUrl = url + "?service=" + serviceKey + "&stdate=" + stDate + "&eddate=" + edDate + "&rows=" + rows + "&cpage=" + cPage;
        
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response;
        PlayDto playDto = null;
        
        try {
            response = restTemplate.getForEntity(apiUrl, String.class);
            if (response.getStatusCode() == HttpStatus.OK) {
                String xmlResponse = response.getBody();
                System.out.println(xmlResponse);

                    // 새로운 DocumentBuilderFactory 생성
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                    // 새로운 DocumentBuilder 생성
                    DocumentBuilder builder = factory.newDocumentBuilder();

                    // XML 응답을 Document 객체로 파싱합니다.
                    Document document = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));

                    // "db" 요소의 목록을 가져옵니다.
                    NodeList dbList = document.getElementsByTagName("db");

                    // "db" 요소를 순회하면서 "mt20id" 태그의 값을 읽어옵니다.
                    for (int i = 0; i < dbList.getLength(); i++) {
                        Element dbElement = (Element) dbList.item(i);
                        String mt20id = dbElement.getElementsByTagName("mt20id").item(0).getTextContent();
                        System.out.println(mt20id);
                       
                        String apiUrl2 = url +"/"+mt20id+"?service=" + serviceKey;
                        
                        RestTemplate restTemplate2 = new RestTemplate();
                        ResponseEntity<String> response2;
                        	response2 = restTemplate2.getForEntity(apiUrl2, String.class);
                        	if (response2.getStatusCode() == HttpStatus.OK) {
                                String xmlResponse2 = response2.getBody();
                                JSONObject jsonObject = XML.toJSONObject(xmlResponse2);

                                
                                // String jsonEx = "{\"id\":1,\"prfnm\":\"타이틀\",\"pcseguidance\":\"공연명 \",\"pcseguidance\":\"가격 \",\"prfpdfrom\":\"2023.06.10\",\"prfpdto\":\"2023.06.10\",\"prfage\":\"나이 \",\"poster\":\"링크 \",\"mt10id\":\"placeid\"}";
                                ObjectMapper objectMapper = new ObjectMapper();
//                              System.out.println("jsonEx : " + jsonEx);
//                              playDto = objectMapper.readValue(jsonEx, PlayDto.class);
                                
                                JsonNode rootNode = objectMapper.readTree(jsonObject.toString());
                                JsonNode dbNode = rootNode.path("dbs").path("db");
                                playDto = objectMapper.readValue(dbNode.toString(), PlayDto.class);
                                
                                String price = playDto.getPcseguidance();
                                String dayTime = playDto.getDtguidance();
                                String priceNumber = price.replaceAll("[^\\d]", "");
                                String day;
                                String time;

								if (priceNumber.isEmpty()) {
								    priceNumber = "0";
								}
								int bracketIndex = dayTime.indexOf("(");
								if (bracketIndex != -1) {
								    // 괄호 이전의 문자열은 요일에 저장
								    day = dayTime.substring(0, bracketIndex).trim();
								    
								    // 괄호 안의 문자열은 시간에 저장 (괄호 제거 후 공백 제거)
								    time = dayTime.substring(bracketIndex + 1, dayTime.length() - 1).trim();
								} else {
								    // 괄호가 없을 경우 예외 처리
								    day = "";
								    time = "";
								}
								System.out.println("******"+day+"-----"+time);
                                playDto.setPrice(priceNumber);
                                
                                playService.insert(playDto);
                                
                                System.out.println("-----");
                                System.out.println("playdto 출력!!!!"+playDto.toString());
                                
                             // 필요한 태그의 값을 가져옵니다.
                             // String prfnm = jsonObject.getJSONObject("db").getString("prfnm");
                                
                                String apiUrl3 = url2 +"/"+playDto.getMt10id() +"?service=" + serviceKey;
                                RestTemplate restTemplate3 = new RestTemplate();
                                ResponseEntity<String> response3;
                                PlaceDto placeDto = null;
                                
                                System.out.println("장소"+playDto.getMt10id());
                                
                                	response3 = restTemplate3.getForEntity(apiUrl3, String.class);
                                	if(response3.getStatusCode()==HttpStatus.OK) {
                                		String xmlResponse3 = response3.getBody();
                                		System.out.println("장소xml "+xmlResponse3);
                                		JSONObject jsonObject2 = XML.toJSONObject(xmlResponse3);
                                		System.out.println("json 출력"+jsonObject.toString());
                                		
                                		ObjectMapper objectMapper2 = new ObjectMapper();
                                		JsonNode rootNode2 = objectMapper2.readTree(jsonObject2.toString());
                                		JsonNode dbNode2 = rootNode2.path("dbs").path("db");
                                		placeDto = objectMapper2.readValue(dbNode2.toString(), PlaceDto.class);
                                		
                                		System.out.println(placeDto.toString());
                                		
                                		placeService.insert(placeDto);
                                		
                                	}else {
                                		System.out.println("통신 실패1: " + response2.getStatusCode());
                                	}   
                        	}else {
                        		System.out.println("통신 실패3: " + response2.getStatusCode());
                        	}  
                    }
            } else {
                System.out.println("통신 실패5: " + response.getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println("통신 실패: " + e.getMessage());
            
        }
        
       return "join";
       
    }
	
}
