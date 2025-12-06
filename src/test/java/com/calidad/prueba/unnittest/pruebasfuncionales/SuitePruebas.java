package com.calidad.prueba.unnittest.pruebasfuncionales;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
    AccesoUady2.class,
    BusquedaGoogleTest.class,
    CreateFuncionalTest.class,         
    CreateSinEmailFuncionalTest.class, 
    ReadFuncionalTest.class,          
    UpdateFuncionalTest.class,        
    DeleteFuncionalTest.class          
})
public class SuitePruebas {
}