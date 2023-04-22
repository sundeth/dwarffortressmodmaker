/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.data;

import br.com.dwarfmod.dwarffortressmodmaker.core.RawFileReader;
import br.com.dwarfmod.dwarffortressmodmaker.core.ResourcesReader;
import java.io.Serializable;
import java.util.Set;
import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 *
 * @author Ander
 */
@Data
@SuperBuilder
public class RawObject implements Comparable, Serializable {
    private String name;
    private String token;
    private String description;
    private Set<RawObject> entries;
    
    @Override
    public String toString() {
        if (this.getName().contains(":")) {
            final String[] tokens = RawFileReader.splitParameter(this.getName());
            try {
                if (RawFileTypeEnum.valueOf(tokens[0]) != null) {
                    return "<html><head></head><body>" + tokens[1] + " <font color='#D3D3D3'>[" + tokens[0] + "]</font></body></html>";                    
                }
            } catch (Exception e) {
            }
        }
        return this.getName();
    }

    @Override
    public int compareTo(Object o) {
        if (o == null || !(o instanceof RawObject)) {
            return -1;
        } else {
            return this.getName().compareTo(((RawObject) o).getName());
        } 
    }
}
