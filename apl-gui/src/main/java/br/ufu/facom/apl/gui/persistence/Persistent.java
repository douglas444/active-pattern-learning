package br.ufu.facom.apl.gui.persistence;

public interface Persistent {
    void reset();
    void load(final XMLConfiguration configuration);
    void save(final XMLConfiguration configuration);
}
