/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infoq.config;

/**
 *
 * @author tiagods
 */
public class DataBaseConfig extends PropsConfig {
    private static DataBaseConfig instance;
    public static DataBaseConfig getInstance() {
        if (instance == null) {
            instance = new DataBaseConfig();
        }
        return instance;
    }
    public DataBaseConfig() {
        super(PropsEnum.GERAL);
    }
}
