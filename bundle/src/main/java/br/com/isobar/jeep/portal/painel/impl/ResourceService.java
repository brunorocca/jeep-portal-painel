package br.com.isobar.jeep.portal.painel.impl;


/**
 * A simple service interface
 */
public interface ResourceService {
    
    /**
     * @return the name of the underlying JCR repository implementation
     */
    public String getRepositoryName();
    
    public String readFromDam(final String fileName);

    public String writeToDam(final String fileName, final String json);
}