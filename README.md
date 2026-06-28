# Vida Fácil - V2 

![Status](https://img.shields.io/badge/status-Refatorado%20(V2)-success)
![JavaFX](https://img.shields.io/badge/UI-JavaFX%20%7C%20SceneBuilder-blue)
![Security](https://img.shields.io/badge/Security-jBCrypt-red)

Este repositório contém a **Versão 2.0 (Refatorada)** do projeto "Vida Fácil", um aplicativo de gerenciamento de medicamentos inicialmente desenvolvido como Projeto de Curricularização para a disciplina de Sistemas de Informação.

A proposta central do projeto continua sendo promover a autonomia e a qualidade de vida da pessoa idosa através do gerenciamento organizado de medicamentos. No entanto, esta nova versão traz uma reestruturação completa da arquitetura, troca de tecnologias de interface, melhorias de segurança e um novo design focado na experiência do usuário.

---

## Funcionalidades Principais

O gerenciamento manual de medicamentos, frequentemente feito em papel ou dependendo apenas da memória, apresenta falhas que podem resultar em esquecimentos ou dosagens incorretas. O **Vida Fácil** atua diretamente nesse problema, oferecendo:

* **Autenticação Segura:** Cadastro e login de usuários protegidos por criptografia para garantir a privacidade dos dados médicos.
* **Gestão de Medicamentos:** Cadastro detalhado de remédios, permitindo registrar o nome, a dosagem exata e a frequência de uso.
* **Monitoramento e Listagem:** Uma interface clara onde o usuário pode visualizar rapidamente todos os medicamentos em uso e suas respectivas rotinas.
* **Foco na Acessibilidade:** Design simplificado e vertical (formato mobile), pensado para facilitar a navegação por pessoas com pouca familiaridade tecnológica.

---

## O que mudou na V2? (Refatoração)

O projeto original cumpriu seu papel acadêmico, mas precisava de uma base mais sólida, moderna e escalável. As principais mudanças desta versão incluem:

* **Gerenciamento de Dependências:** Adoção do **Maven** para facilitar a compilação e o gerenciamento de bibliotecas externas.
* **Nova Interface Gráfica (UI):** Substituição do antigo Java Swing por **JavaFX** integrado com o **SceneBuilder**.
* **Redesign e Layout Mobile:** A interface desktop antiga (tons verdes) foi totalmente redesenhada para um formato "mobile-first" vertical, adotando uma identidade visual mais limpa e moderna com tons de azul.
* **Arquitetura Limpa:** O código foi reorganizado em uma estrutura de pacotes bem definida (MVC/Camadas), separando responsabilidades em `application`, `controller`, `database`, `model`, `services` e `utils`.
* **Foco no Core Business:** Funcionalidades secundárias da versão antiga (como a parte de hidratação/metas diárias) e a integração de **Cuidadores** não foram implementadas nesta etapa. O foco foi direcionado inteiramente para garantir a excelência e o funcionamento da funcionalidade principal: o monitoramento e gerenciamento de remédios para o usuário final.

---

## Segurança Adicional (jBCrypt)

Uma das grandes novidades desta versão é a preocupação com a segurança dos dados. O projeto agora conta com a biblioteca **`org.mindrot.jbcrypt.BCrypt`** para realizar o *hash* e *salt* das senhas dos usuários antes de salvá-las, garantindo que nenhuma credencial fique exposta em texto plano no banco de dados.

---

## Estratégia de Banco de Dados

### Cenário Atual (V2)

O banco de dados MySQL original em nuvem foi substituído pelo **SQLite**. Os dados agora são salvos localmente em arquivos. Essa decisão arquitetural foi tomada para agilizar os testes e o desenvolvimento, eliminando a necessidade de configurar conexões externas e depender de internet durante a codificação.

### Roadmap e Futuro (V3)

O objetivo futuro é implementar um sistema de **duas camadas de dados (Offline-First / Sync):**

1. Utilizar o banco de dados **H2** embarcado para armazenamento rápido no PC/Local.
2. Criar uma rotina de sincronização que enviará os dados armazenados localmente para um **Banco de Dados na Nuvem** sempre que houver conexão disponível.

---

## Tecnologias Utilizadas

* **Java** (Linguagem principal)
* **JavaFX & SceneBuilder** (Construção da Interface Gráfica)
* **Maven** (Automação e Gerenciamento de Dependências)
* **SQLite** (Banco de dados relacional local)
* **jBCrypt** (Criptografia de senhas)

---

## Desenvolvedores

O projeto original e toda a sua refatoração para a versão 2.0 foram desenvolvidos em conjunto pela seguinte equipe:

* Caio Henrique Felix dos Reis Lopes
* Maria Eduarda Ferreira Santos
* Miguel dos Santos Conforte
* Nickolas Aranha Martinez
* Nicolas Yuji Hiratani