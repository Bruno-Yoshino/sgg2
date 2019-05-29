/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

/**
 *
 * @author 吉野　廉
 * @author 羽根川　翼
 */
public class Validacao 
{
    public int ValidaTexto(String texto)//1 == "" | 0 == OK
    {
        if(texto.trim().equals(""))
        {
            return 1;
        }
        return 0;
    }        
     
    public int ConverteNumeroInteiro(String texto)// return == -999 -> Nuber Invalid | return != -999 -> OK
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
    
    public double ConverteNumeroReal(String texto)// return == -999 -> Nuber Invalid | return != -999 -> OK
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
    
    public boolean ValidarDataMenorAtual(LocalDate data)
    {
        return data.isBefore(LocalDate.now());
    }
    
    public boolean ValidarDataDuasData(LocalDate dataI, LocalDate dataF)
    {
        return dataI.isBefore(dataF);
    }
    public boolean ValidarDataMenorAtual(Date data)
    {
        return data.before(Date.from(Instant.now()));
    }
    
    public boolean ValidarDataDuasData(Date dataI, Date dataF)
    {
        return dataI.before(dataF);
    }
}
