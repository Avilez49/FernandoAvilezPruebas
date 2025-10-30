package com.calidad.prueba.unnittest.calculadora;

import org.hamcrest.Matcher;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.calidad.prueba.unittest.calculadora.Calculadora;

public class CalculadoraTest {

        public double operador1;
        public double operador2;
        public Calculadora calc = new Calculadora();  
    
    // Pasos que deben ocurrir antes y despues de cada operación.
        @BeforeEach
    void setup(){
        operador1 = 10;
        operador2 = 5;    
        calc = new Calculadora();
        System.out.println("Vicky es tan AAAAAAAHAHHH!!! ⚡⚡");
    }

    @AfterEach
    public void cleanUp(){
        System.out.println("Prueba finalizada........");
    }

    // Los Test son utiles para la creación de pruebas.
    // AssertEquals y AsserThat nos permite obtener los resultados y la comparación con los resultadoEsperando.
    @Test // Inicio del Metodo de Suma 
        void testSumaNumerosPositivos(){
            // 1. Inicializar los datos/variables
            double resultadoEsperado = 15;

            // 2. LLamar el método/Ejercitar el Método
            double resultado = calc.suma(operador1, operador2);

            // 3. Verificación del Resultado
            assertThat(resultadoEsperado, is(resultado));
        }
    

    @Test // Inicio del Método de Resta
        void testRestaNumerosPositivos(){
            // 1.
            double operador1 = 10;
            double operador2 = 5;
            double resultadoEsperado = 5;

            // 2.
            double resultado = calc.resta(operador1, operador2);

            // 3.
            assertThat(resultadoEsperado, is(resultado));

        }

    @Test // Inicio del Método Multiplicación
        void testMultiplicaciónNumerosPositivos(){
            // 1.
            double operador1 = 10;
            double operador2 = 2;
            double resultadoEsperado = 20;
            
            // 2.
            double resultado = calc.multiplica(operador1, operador2);

            // 3. 
            assertThat(resultadoEsperado, is(resultado));

        }

    /*
        @Test(expected = NullPointerException.class)
    public void whenExceptionThrown_thenExpectationSatisfied(){
        String test = null;
        test.length();
    } 
    */
           
}
