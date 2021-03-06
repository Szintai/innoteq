package com.Innoteq.innoteq.service;

import com.Innoteq.innoteq.model.Item;
import com.Innoteq.innoteq.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemServiceImp implements ItemService {

    private final ItemRepository itemRepository;

    public ItemServiceImp(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findById(Long id) {
        return itemRepository.findById(id).orElse(new Item());
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }
}
