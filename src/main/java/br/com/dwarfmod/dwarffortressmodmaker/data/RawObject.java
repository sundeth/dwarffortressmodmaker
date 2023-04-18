/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.data;

import java.util.Set;
import lombok.Builder;
import lombok.Data;

/**
 *
 * @author Ander
 */
@Data
@Builder
public class RawObject implements Comparable<RawObject> {
    private String name;
    private String objectClass;
    private String description;
    private Set<RawObject> entries;
    
    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public int compareTo(final RawObject o) {
        return this.getName().compareTo(o.getName());
    }
}
