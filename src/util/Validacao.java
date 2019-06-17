/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import javafx.scene.control.TextField;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 */
public class Validacao 
{
    public final int ValidaTexto(String texto)//1 == "" | 0 == OK
    {
        if(texto.trim().equals(""))
        {
            return 1;
        }
        return 0;
    }        
     
    public final int ConverteNumeroInteiro(String texto)// return == -999 -> Nuber Invalid | return != -999 -> OK
    {
        try 
        {
            return Integer.parseInt(texto);
        } 
        catch (NumberFormatException e) 
        {
            return -999;
        }
    } 
    
    public final int ConverteNumeroInteiro(Object obj)// return == -999 -> Nuber Invalid | return != -999 -> OK
    {
        try 
        {
            return Integer.parseInt(String.valueOf(obj));
        } 
        catch (NumberFormatException e) 
        {
            return -999;
        }
    } 
    
    public final double ConverteNumeroReal(String texto)// return == -999 -> Nuber Invalid | return != -999 -> OK
    {
        try 
        {
            return Double.parseDouble(texto);
        } 
        catch (NumberFormatException e) 
        {
            return -999;
        }
    }
    
    public final double ConverteNumeroReal(Object obj)// return == -999 -> Nuber Invalid | return != -999 -> OK
    {
        try 
        {
            return Double.parseDouble(String.valueOf(obj));
        } 
        catch (NumberFormatException e) 
        {
            return -999;
        }
    }
    
    public final boolean ValidarDataMenorAtual(LocalDate data)
    {
        return data.isBefore(LocalDate.now());
    }
    
    public final boolean ValidarDataDuasData(LocalDate dataI, LocalDate dataF)
    {
        return dataI.isBefore(dataF);
    }
    public final boolean ValidarDataMenorAtual(Date data)
    {
        return data.before(Date.from(Instant.now()));
    }
    
    public final boolean ValidarDataDuasData(Date dataI, Date dataF)
    {
        return dataI.before(dataF);
    }
    
    public final boolean ValidarEmail(String email)
    {
        String mailFormat = "^[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+(\\.[a-zA-Z0-9!#$%&'_`/=~\\*\\+\\-\\?\\^\\{\\|\\}]+)*+(.*)@[a-zA-Z0-9][a-zA-Z0-9\\-]*(\\.[a-zA-Z0-9\\-]+)+$";
        return email.matches(mailFormat);
    }
    
    public final int PrimeiroDigito(String cpf)
    {
        int soma = 0, resultado = 0, resto = 0, num;
        for(int i = 0, x = 10; i < 9; i++, x--)
        {
            num = Character.digit(cpf.charAt(i), 10);
            soma = soma + num * x; 
        }
        resto = soma%11;
        resultado = 11 - resto;
        if(resultado == 10 || resultado == 11)
        {
            return 0;
        }
        return resultado;
    }

    public final int SegundoDigito(String cpf)
    {
        int soma = 0, resultado = 0, resto = 0, num;
        for(int i = 0, x = 11; i < 10; i++, x--)
        {
            num = Character.digit(cpf.charAt(i), 10);
            soma = soma + num * x; 
        }
        resto = soma%11;
        resultado = 11 - resto;
        if(resultado == 10 || resultado == 11)
        {
            return 0;
        }
        return resultado;
    }
}
