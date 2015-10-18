package ex.core.json.conversor;

import java.util.Map;

import ex.core.json.conversor.exception.JSonConversorException;

public interface JSonConversor {

	public String converte(Object object) throws JSonConversorException;
	public Map converte(String json) throws JSonConversorException;
}
