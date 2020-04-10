/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 *
 * @author tiagods
 */
public class PropsConfig implements PropsInterface {

    private static Properties props = null;
    private static InputStream stream = null;
    public PropsConfig(PropsEnum propsEnum) {
        props = new Properties();
        fileLoad(propsEnum);
    }

    @Override
    public void fileLoad(PropsEnum propsEnum) {
        try {
            stream = getClass().getClassLoader().getResource(propsEnum.getDescricao()).openStream();
            props.load(stream);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Falha ao carregar o arquivo de configurações do Banco de Dados - Atualizador");
        }
    }
    @Override
    public String getValue(String key) {
        return props.getProperty(key);
    }
    @Override
    public boolean containskey(String key){
        return props.containsKey(key);
    }
    public Map<String, String> getMap() {
        Map<String, String> map = new HashMap<>();
        props.keySet().forEach(c -> map.put(c.toString(), getValue(c.toString())));
        return map;
    }
}
