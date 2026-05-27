package br.com.clinica.openfire.plugin;

import org.jivesoftware.openfire.container.Plugin;
import org.jivesoftware.openfire.container.PluginManager;
import org.jivesoftware.openfire.user.PresenceEventListener;
import org.jivesoftware.openfire.user.PresenceEventDispatcher;
import org.jivesoftware.openfire.session.ClientSession;
import org.jivesoftware.openfire.session.Session;
import org.jivesoftware.openfire.SessionManager;
import org.xmpp.packet.Presence;
import org.xmpp.packet.JID;
import java.io.File;

public class StationNamePlugin implements Plugin, PresenceEventListener {

    @Override
    public void initializePlugin(PluginManager manager, File pluginDir) {
        // Registra o ouvinte no despachante de eventos do Openfire
        PresenceEventDispatcher.addListener(this);
    }

    @Override
    public void destroyPlugin() {
        // Remove o ouvinte ao desativar o plugin
        PresenceEventDispatcher.removeListener(this);
    }

    @Override
    public void presenceChanged(ClientSession session, Presence presence) {
        if (session == null || presence == null) {
            return;
        }

        // Desconsidera pacotes de logoff (Unavailable)
        if (presence.getType() == Presence.Type.unavailable) {
            return;
        }

        JID jid = session.getAddress();
        if (jid != null) {
            String stationName = jid.getResource();

            if (stationName != null && !stationName.isEmpty()) {
                String currentStatus = presence.getStatus();
                String newStatus;

                // Se o usuário já possui mensagem customizada escrita
                if (currentStatus != null && !currentStatus.isEmpty()) {
                    if (!currentStatus.contains(stationName)) {
                        newStatus = currentStatus + " - " + stationName;
                    } else {
                        return; // Evita loop se o evento disparar novamente
                    }
                } else {
                    // Trata estados padrões mapeando o indicador de exibição (Show)
                    if (presence.getShow() != null) {
                        String showState = presence.getShow().toString();
                        if ("away".equalsIgnoreCase(showState)) {
                            newStatus = "Ausente - " + stationName;
                        } else if ("dnd".equalsIgnoreCase(showState)) {
                            newStatus = "Ocupado - " + stationName;
                        } else {
                            newStatus = "- " + stationName;
                        }
                    } else {
                        newStatus = "- " + stationName;
                    }
                }

                // Injeta diretamente no pacote gerenciado pelo servidor
                presence.setStatus(newStatus);
            }
        }
    }

    // Métodos obrigatórios da interface PresenceEventListener que não precisamos tratar neste escopo
    @Override
    public void availableSession(ClientSession session, Presence presence) {}

    @Override
    public void unavailableSession(ClientSession session, Presence presence) {}

    @Override
    public void subscribedToPresence(JID tableUser, JID userToCheck) {}

    @Override
    public void unsubscribedToPresence(JID tableUser, JID userToCheck) {}
}