package com.nexmo.hm.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import com.nexmo.hm.utils.GameStatusEnum;

@Converter
public class GameStatusEnumConvertor implements    
AttributeConverter<GameStatusEnum,Integer>{
	
    @Override
    public Integer convertToDatabaseColumn(GameStatusEnum enumValue) {
    	return enumValue.getId();
    }

    @Override
    public GameStatusEnum convertToEntityAttribute(Integer entityValue) {
        return GameStatusEnum.fromId(entityValue);
    }
}
