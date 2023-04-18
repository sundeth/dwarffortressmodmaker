/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.dwarfmod.dwarffortressmodmaker.data.library;

import br.com.dwarfmod.dwarffortressmodmaker.data.RawFileTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Ander
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Token {
    private RawFileTypeEnum type;
    private String token;
    private String arguments;
    private String context;
    private String description;
    
    @Override
    public String toString() {
        return "Token{" +
                "type=" + type +
                ", token='" + token + '\'' +
                ", arguments='" + arguments + '\'' +
                ", context='" + context + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
