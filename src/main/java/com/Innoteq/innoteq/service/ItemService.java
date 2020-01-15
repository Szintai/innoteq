package com.Innoteq.innoteq.service;

import com.Innoteq.innoteq.model.Item;

import java.util.List;

public interface ItemService {

    Item save(Item item);

    Item findById(Long id);

    List<Item> findAll();

}
