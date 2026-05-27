# Openfire Station Name In Roster Plugin

<img width="579" height="998" alt="image" src="https://github.com/user-attachments/assets/0e52b351-6a9e-4e62-9ff8-10274107c274" />

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

## 💾 Download e Instalação Rápida (Para Administradores de TI)

Se você não deseja compilar o código-fonte e quer apenas instalar o plugin pronto no seu servidor, siga os passos abaixo:

### 1. Baixar o Plugin Compilado
Acesse a seção de [Releases](https://github.com/GuilhermeSt12/openfire-station-name-plugin/releases) do repositório e faça o download do arquivo executável:
* **`station-name-plugin-1.0.0.jar`**

### 2. Instalação no Servidor Openfire
1. Abra o Console de Administração Web do seu servidor **Openfire**.
2. No menu superior, navegue até a aba **Plugins**.
3. No menu lateral esquerdo, clique em **Administração de Plugins** (Plugin Management).
4. Role a página até a seção **Enviar Plugin** (Upload Plugin).
5. Clique em *Escolher arquivo*, selecione o arquivo `station-name-plugin-1.0.0.jar` que você baixou e clique em **Enviar** (Upload).
6. O Openfire irá descompactar e ativar o plugin automaticamente em segundo plano.

### 3. Validação
Para confirmar o sucesso da instalação, verifique se o plugin **"Station Name In Roster"** aparece na listagem de plugins ativos com o status normalizado. A partir deste momento, o monitoramento das estações de trabalho entrará em execução imediatamente.
