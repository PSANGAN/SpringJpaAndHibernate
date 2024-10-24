package com.jpa.mssql.poc.demoJpawithMSSql.Listener;

import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;

public class PersistEntityListener {
    @PostLoad
    public void logMessage(Object entityInstance) {

       System.out.println(entityInstance.toString());
    }

}
