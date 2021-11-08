package org.iesfm.instituto;

import org.iesfm.instituto.InstitutoConfiguration;
import org.iesfm.instituto.Title;
import org.iesfm.instituto.dao.TitleDAO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {


    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(
                        InstitutoConfiguration.class
                );

        TitleDAO program = context.getBean(TitleDAO.class);
        List<Title> titles = program.list();
        for(Title title: titles) {
            System.out.println(title.toString());
        }
    }
}
