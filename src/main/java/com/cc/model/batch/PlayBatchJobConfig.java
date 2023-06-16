//package com.cc.model.batch;
//
//import com.cc.model.dto.PlayDto;
//import com.cc.model.service.PlayService;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.json.JSONObject;
//import org.json.XML;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
//import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
//import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.client.RestTemplate;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import java.io.ByteArrayInputStream;
//
//@Configuration
//@EnableBatchProcessing
//public class PlayBatchJobConfig {
//    private static final String URL = "http://www.kopis.or.kr/openApi/restful/pblprfr";
//    private static final String SERVICE_KEY = "722233b68ffa4883ae5213ccf16565a5";
//    private static final String NAME = "PF132236";
//
//    @Autowired
//    private JobBuilderFactory jobBuilderFactory;
//
//    @Autowired
//    private StepBuilderFactory stepBuilderFactory;
//
//    @Autowired
//    private PlayService playService;
//
//    @Bean
//    public Job fetchPlayDataJob(Step fetchPlayDataStep) {
//        return jobBuilderFactory.get("fetchPlayDataJob")
//                .incrementer(new RunIdIncrementer())
//                .flow(fetchPlayDataStep)
//                .end()
//                .build();
//    }
//
//    @Bean
//    public Step fetchPlayDataStep(ItemProcessor<String, PlayDto> fetchPlayDataProcessor,
//                                  ItemWriter<PlayDto> fetchPlayDataWriter) {
//        return stepBuilderFactory.get("fetchPlayDataStep")
//                .<String, PlayDto>chunk(1)
//                .reader(() -> {
//                    // API 호출 및 XML 응답 데이터를 반환하는 reader
//                    RestTemplate restTemplate = new RestTemplate();
//                    String apiUrl = URL + "?service=" + SERVICE_KEY + "&name=" + NAME;
//                    ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
//                    if (response.getStatusCode() == HttpStatus.OK) {
//                        return response.getBody();
//                    } else {
//                        throw new RuntimeException("API request failed with status code: " + response.getStatusCode());
//                    }
//                })
//                .processor(fetchPlayDataProcessor)
//                .writer(fetchPlayDataWriter)
//                .build();
//    }
//
//    @Bean
//    public ItemProcessor<String, PlayDto> fetchPlayDataProcessor() {
//        return xmlResponse -> {
//            try {
//                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//                DocumentBuilder builder = factory.newDocumentBuilder();
//                Document document = builder.parse(new ByteArrayInputStream(xmlResponse.getBytes()));
//                NodeList dbList = document.getElementsByTagName("db");
//
//                ObjectMapper objectMapper = new ObjectMapper();
//                ItemProcessor<String, PlayDto> itemProcessor = jsonEx -> objectMapper.readValue(jsonEx, PlayDto.class);
//
//                for (int i = 0; i < dbList.getLength(); i++) {
//                    Element dbElement = (Element) dbList.item(i);
//                    String mt20id = dbElement.getElementsByTagName("mt20id").item(0).getTextContent();
//                    String apiUrl = URL + "/" + mt20id + "?service=" + SERVICE_KEY;
//
//                    RestTemplate restTemplate = new RestTemplate();
//                    ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);
//                    if (response.getStatusCode() == HttpStatus.OK) {
//                        String xmlResponse2 = response.getBody();
//                        JSONObject jsonObject = XML.toJSONObject(xmlResponse2);
//                        JsonNode rootNode = objectMapper.readTree(jsonObject.toString());
//                        JsonNode dbNode = rootNode.path("dbs").path("db");
//                        String jsonEx = dbNode.toString();
//
//                        PlayDto playDto = itemProcessor.process(jsonEx);
//                        playService.insert(playDto);
//                    } else {
//                        throw new RuntimeException("API request failed with status code: " + response.getStatusCode());
//                    }
//                }
//
//                return null;
//            } catch (Exception e) {
//                throw new RuntimeException("Error occurred while processing the XML response: " + e.getMessage(), e);
//            }
//        };
//    }
//
//    @Bean
//    public ItemWriter<PlayDto> fetchPlayDataWriter() {
//        return playDtoList -> {
//            // playDtoList를 처리하는 writer
//            for (PlayDto playDto : playDtoList) {
//                playService.insert(playDto);
//            }
//        };
//    }
//}
//
