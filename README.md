# Openfire Station Name In Roster Plugin

Plugin de alto impacto e leve para o servidor XMPP Openfire que injeta dinamicamente o nome da estação de trabalho (Resource) no status do usuário em tempo real.

## 🚀 A Solução

Em ambientes corporativos, administradores de TI e SysAdmins frequentemente precisam identificar em qual computador físico um determinado usuário está autenticado. 

Esta solução elimina a necessidade de buscar logs em bancos de dados ou inspecionar sessões ativas no painel de controle. O plugin intercepta os pacotes de presença (`Presence`) no lado do servidor e anexa o **XMPP Resource** (mapeado pelo Spark como o hostname do Windows) diretamente na string de status distribuída para toda a rede.

### Características
* **Pegada Zero no Cliente:** Funciona 100% server-side. Não requer modificações ou atualizações nos clientes Spark.
* **Concatenação Inteligente:** Respeita mensagens customizadas ou ramais digitados manualmente pelo usuário, adicionando a estação ao final (`Status Customizado - NOME-DA-MAQUINA`).
* **Suporte Amplo de Presença:** Intercepta e atualiza os estados `Disponível` (Online), `Ausente` (Away) e `Não Perturbe` (DND).
* **Arquitetura Isolada:** Desenvolvido com um interceptor interno estático desacoplado, neutralizando falhas crônicas de Classloader do Openfire (`ClassNotFoundException`).

---

## 🛠️ Requisitos

* **Openfire Server:** 5.0.0 ou superior
* **Java JDK:** 17
* **Apache Maven:** 3.8+

---

## 📦 Como Compilar e Empacotar

1. Clone o repositório:
   ```bash
   git clone [https://github.com/GuilhermeSt12/openfire-station-name-plugin.git](https://github.com/GuilhermeSt12/openfire-station-name-plugin.git)
   cd openfire-station-name-plugin