package com.salon.community.mapper;

import com.salon.community.model.Keys;

public interface KeysExtMapper {
    int ChangeStatus(Keys record);
    Keys selectByKey(String key);
}
