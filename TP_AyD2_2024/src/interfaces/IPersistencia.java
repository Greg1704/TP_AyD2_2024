package interfaces;

import java.util.List;

import modelo.Cliente;

public interface IPersistencia {
	void saveLog(String log);
    void saveClientInfo(Cliente cliente);
}
