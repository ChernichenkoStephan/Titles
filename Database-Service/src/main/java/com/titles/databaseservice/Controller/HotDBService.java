package com.titles.databaseservice.Controller;

import com.titles.databaseservice.Controller.Interfacies.HotDataBase;
import com.titles.databaseservice.Controller.Interfacies.HotRepository;
import com.titles.databaseservice.Model.Content;
import com.titles.databaseservice.Model.Mem;
import com.titles.databaseservice.Model.HotPaper;
import com.titles.databaseservice.Model.Paper;

import java.util.*;

public class HotDBService implements HotDataBase {
    private HotDBService(){}
    public static HotDBService defaultService = new HotDBService();
    private HotRepository hotRepository;
    public void init(HotRepository hotRepository){ this.hotRepository = hotRepository;
    }


//    public void deleteAll() { hotRepository.deleteAll();}
//    public void save(HotPaper paper) { hotRepository.save(paper);}

    @Override
    public Set<Paper> findAll() {
//        Iterable<HotPaper> temp = hotRepository.findAll();
        Set<Paper> sp = new HashSet<>();
        for(HotPaper hp: hotRepository.findAll())
        {
            sp.add(hp.toPaper());
        }
        return sp;
    }

    @Override
    public void savePaper(Set<Paper> paper) {
        for(Paper p : paper)
        {
            HotPaper hp = p.toHot();
            Integer count = hotRepository.findAll().size();
            hp.setHpID(count);
            hotRepository.save(hp);
        }
    }
    @Override
    public Paper findByID(Integer paperID){
        for(HotPaper hp : hotRepository.findAll())
        {
            if(hp.getHpID().equals(paperID)){ return hp.toPaper();}
        }
        return new Paper(true,1,new Date(),"source",2,Set.of(new Mem()),1L,"author","title","description","body","content",Set.of(new Content()));
    }
    @Override
    public List<HotPaper> findAllForTime()
    {
        return hotRepository.findAll();
    }
    @Override
    public void saveHpPaper(HotPaper paper, Integer paperID)
    {
        for(HotPaper hp : hotRepository.findAll())
        {
            if(hp.getHpID().equals(paperID))
            {
                hotRepository.delete(hp);
            }
        }
        paper.setHpID(paperID);
        hotRepository.save(paper);
    }

    @Override
    public void saveOnePaper(Paper paper)
    {

    }
    @Override
    public void deleteByID(Integer paperID)
    {
        for(HotPaper hp : hotRepository.findAll())
        {
            if(hp.getHpID().equals(paperID))
            {
                hotRepository.delete(hp);
            }
        }
    }
    @Override
    public void deleteAll(Integer key)
    {
        if(key==228){ hotRepository.deleteAll();}
    }
    @Override
    public List<String> findMem()
    {
        List<String> mems = new ArrayList<>();
        for(HotPaper hp : hotRepository.findAll())
        {
            for (Mem mem : hp.getMems())
            {
                mems.add(mem.getName());
            }
        }
        return mems;
    }
}
