package com.salon.community.service;

import com.salon.community.mapper.KeysExtMapper;
import com.salon.community.mapper.KeysMapper;
import com.salon.community.model.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class KeyService {
    @Autowired
    private KeysMapper keysMapper;
    @Autowired
    private KeysExtMapper keysExtMapper;

    public void ChangeStatus(Long id) {
        Keys keys=new Keys();
        keys.setId(id);
        keysExtMapper.ChangeStatus(keys);
    }

    public Keys selectByKey(String key) {
        Keys keys = keysExtMapper.selectByKey(key);
        return keys;
    }


}
