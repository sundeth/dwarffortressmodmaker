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
public class RawFile implements Comparable<RawFile> {
    private String fileName;
    private String fullPath;
    private String id;
    private String description;
    private RawFileTypeEnum type;
    private Set<RawObject> objects;

    @Override
    public int compareTo(RawFile o) {
        return this.getId().compareTo(o.getId());
    }
    
    @Override
    public String toString() {
        return this.getId();
    }
}