package com.cc.model.mapper;

import org.springframework.stereotype.Component;

import com.cc.model.dto.PlaceDto;
import com.cc.model.entity.Place;

@Component
public class PlaceMapper {
	public PlaceDto toDto(Place place) {
        PlaceDto placeDto = new PlaceDto();
        placeDto.setMt10id(place.getPlaceId());
        placeDto.setFcltynm(place.getPlaceName());
        placeDto.setAdres(place.getPlaceAddr());
        placeDto.setTelno(place.getPlacePhone());
        placeDto.setRelateurl(place.getPlaceLink());
        placeDto.setLa(place.getLa());
        placeDto.setLo(place.getLo());
        //placeDto.setMt10id(place.getPlaceApiId());
        return placeDto;
    }

    public Place toEntity(PlaceDto placeDto) {
        Place place = new Place();
        place.setPlaceId(placeDto.getMt10id());
        place.setPlaceName(placeDto.getFcltynm());
        place.setPlaceAddr(placeDto.getAdres());
        place.setPlacePhone(placeDto.getTelno());
        place.setPlaceLink(placeDto.getRelateurl());
        place.setLa(placeDto.getLa());
        place.setLo(placeDto.getLo());
       // place.setPlaceApiId(placeDto.getMt10id());
        return place;
    }
}

//@Mapper
//public interface PlaceMapper {
//    PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);
//    
//    @Mapping(target = "placeId", ignore = true)
//    @Mapping(target = "placeName", source = "fcltynm")
//    @Mapping(target = "placeAddr", source = "adres")
//    @Mapping(target = "placePhone", source = "telno")
//    @Mapping(target = "placeLink", source = "relateurl")
//    @Mapping(target = "placeNickname", ignore = true)
//    Place placeDtoToEntity(PlaceDto placeDto);
//    
//    @Mapping(target = "id", source = "placeId")
//    @Mapping(target = "fcltynm", source = "placeName")
//    @Mapping(target = "adres", source = "placeAddr")
//    @Mapping(target = "telno", source = "placePhone")
//    @Mapping(target = "relateurl", source = "placeLink")
//    @Mapping(target = "mt10id", ignore = true)
//    PlaceDto placeEntityToDto(Place place);
//}
