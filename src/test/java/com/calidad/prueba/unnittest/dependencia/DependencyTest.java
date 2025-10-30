package com.calidad.prueba.unnittest.dependencia;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import static org.mockito.Mockito.when;

public class DependencyTest {

    private Dependency dependency;
    private SubDependency subDependency;

    @BeforeEach
    void setUp() throws  Exception{
        //Setear mock
        subDependency = mock(SubDependency.class);
        dependency = new Dependency(subDependency);
    }

    @Test
    void test(){
        //System.out.println(subDependency.getClassName());
        //Resultado esperado
        String esperado = "SubDependency.class";

        when(subDependency.getClassName()).thenReturn(esperado);
        //Ejerciccio
        String resultadoEjecucion = dependency.getSubdependencyClassName();

        //Verificación
        assertThat(resultadoEjecucion,is(esperado));
    }

    @Test
    void testSumaDos(){
        //Resultado esperado
        int esperado = 12;
        when(subDependency.addTwo(10)).thenReturn(12);

        int resultadoEjecucion = subDependency.addTwo(10);
        // Verificación
        assertThat(resultadoEjecucion,is(esperado));
    }

    @Test
        void testAddTwo(){
            when(subDependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>(){
                public Integer answer(InvocationOnMock invoction) throws Throwable{
                    int arg = (Integer) invoction.getArguments()[0];
                    return arg +20;
                }
            });
            Integer resultadoEjecucion = subDependency.addTwo(100);
            assertThat(120, is(resultadoEjecucion));
    }    

}


