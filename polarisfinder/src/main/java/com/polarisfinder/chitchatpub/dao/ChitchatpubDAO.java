package com.polarisfinder.chitchatpub.dao;

import java.util.List;

import com.polarisfinder.chitchatpub.entity.Chitchatpub;
import com.polarisfinder.chitchatpub.entity.Chitchatpubstar;

public interface ChitchatpubDAO {

    void createChitchatpub(Chitchatpub chitchatpub);
	List<Chitchatpub> getChitchatpubByPlacelocation(Chitchatpub chitchatpub, int paging);
    void createChitchatpubstar(Chitchatpubstar chitchatpubstar);
    Chitchatpubstar getChitchatpubstar(Chitchatpubstar chitchatpubstar);
    Chitchatpub getChitchatpubById(int chitchatpub_id);
    void increaseChitchatpubstarcnt(Chitchatpub chitchatpub);
}
