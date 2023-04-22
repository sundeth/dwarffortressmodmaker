/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.data;

import java.io.Serializable;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author Ander
 */
@Data
@SuperBuilder
public class RawFile extends RawObject implements Serializable {
    private RawFileTypeEnum type;
    
    @Override
    public String toString() {
        return "<html><head></head><body>" + this.getName() + " <font color='#D3D3D3'>[" + type.name() + "]</font></body></html>";
    }
    
    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof RawFile)) {
            return -1;
        } else {
            return this.getName().compareTo(((RawFile) o).getName());
        } 
    }
}