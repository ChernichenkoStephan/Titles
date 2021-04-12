package com.titles.databaseservice.cold;

import com.titles.databaseservice.Model.ColdPaper;
import com.titles.databaseservice.Model.Paper;
import com.titles.databaseservice.Model.HotPaper;

import java.util.*;

public class ColdDBService implements ColdDataBase {
    private ColdDBService(){}
    public static ColdDBService defaultService = new ColdDBService();
    private ColdRepository coldRepository;
    public void init(ColdRepository coldRepository){ this.coldRepository = coldRepository; }

    @Override
    public Set<Paper> findAll() {
        Set<Paper> sp = new HashSet<>();
        for(ColdPaper cp: coldRepository.findAll())
        {
            sp.add(cp.toPaper());
        }
        return sp;
    }

    @Override
    public void savePaper(Set<Paper> paper) {
        for(Paper p : paper)
        {
            ColdPaper cp = p.toCold();
            coldRepository.save(cp);
        }
    }
    @Override
    public Paper findByID(Integer id){
//        for(ColdPaper cp : coldRepository.findAll())
//        {
//            System.out.println(cp.getId());
//            System.out.println(cp.getAuthor());
//            if(cp.getId().equals(id)){
//                System.out.println(cp.toString());
//                return cp.toPaper();}
//        }
//        return new Paper(false,1,1L,"source",2,Set.of(new Mem()),1L,"author","title","description","body","content",Set.of(new Content()));
        return null;
    }
    @Override
    public List<HotPaper> findAllForTime()
    {
        return null;
    }
    @Override
    public void saveHpPaper(HotPaper paper, Integer paperID)
    {
    }
    @Override
    public void saveOnePaper(Paper paper)
    {

    }
    @Override
    public void deleteByID(Integer paperID)
    {

    }
    @Override
    public void deleteAll(Integer key)
    {
    }
    @Override
    public List<String> findMem(){return null;}



//
//    public void deleteAll() { coldRepository.deleteAll();}
//    public void save(ColdPaper paper) { coldRepository.save(paper);}
}
