package com.poly.bean;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.poly.dao.BaihocDAO;
import com.poly.dao.QuizDAO;
import com.poly.service.BaihocService;
import com.poly.service.ChuonghocService;
import com.poly.service.QuizService;
import com.poly.service.QuizServiceBean;

@Configuration
public class ConfigBeangetQuiz {
    @Autowired
    QuizDAO quizDAO;

    @Autowired
    BaihocDAO baihocDAO;

    @Autowired
    ChuonghocService chuonghocService;

    @Autowired
    BaihocService baihocService;

    @Autowired
    QuizService quizService;
    
    @Bean(name = "getQuizByid")
    public QuizServiceBean getAllbyProductandUsername() {
        QuizServiceBean quizServiceBean = new QuizServiceBean() {
            @Override
            public List<Quiz> getAllDaxong(int productid, String username, int idbaihoc) {
                return quizDAO.getAllbyProductandUsername(productid, username, idbaihoc);
            }

            @Override
            public List<Quiz> getAllByBaihocId(int idbaihoc) {
                return quizDAO.getAllByBaihocId(idbaihoc);
            }

            @Override
            public List<Baihoc> getAllChuaxong(int productid, int idchuonghoc, String username) {
                List<Baihoc> listbaihocchuaxong= new ArrayList<>();
                List<Baihoc> listbaihoc = baihocService.getAllByChuonghoc(idchuonghoc);
                for (int i = 0; i < listbaihoc.size(); i++) {
                    int idbaihoc = listbaihoc.get(i).getBaihocid();
                    List<Quiz> listQuizByIDbaihoc = quizService.getAllByBaihocId(idbaihoc);
                    List<Quiz> listDahoanthanhByUser = quizDAO.getAllbyProductandUsername(productid, username,
                            idbaihoc);
                    if (listQuizByIDbaihoc.size() == 0) {
                        System.out.println("ràng buộc chưa thêm quiz cho bài học");
                    } else {
                        if (listQuizByIDbaihoc.size() != listDahoanthanhByUser.size()) {
                            // int chuonghocid = listbaihoc.get(i).getChuonghoc().getId();
                            Baihoc baihoc = listbaihoc.get(i);
                            listbaihocchuaxong.add(baihoc);
                        }
                    }
                }

                return listbaihocchuaxong;
            }

        };
        return quizServiceBean;
    }

}