# VidaFácil

![Status](https://img.shields.io/badge/status-protótipo%20acadêmico-blue)

Este projeto foi desenvolvido como **Projeto de Curricularização** para a disciplina de **Sistemas de Informação**, com foco na aplicação prática dos conceitos de **Engenharia de Software**.

---

## Tecnologias e Ferramentas

Para a modelagem e estruturação do sistema, utilizamos as seguintes tecnologias e ferramentas:

- **Java** – Linguagem de programação utilizada como linguagem principal para implementar a lógica do sistema e a interface gráfica da aplicação (com a utilização do Swing como biblioteca gráfica e a ferramenta do WindowBuilder para facilitar a construção das telas).
- **JDBC** – Tecnologia utlizada para realizar a conexão entre a aplicação Java e o banco de dados. 
- **MySQL** – Sistema de Gerenciamento de Banco de Dados  
- **SQL** – Linguagem para estruturação e scripts do banco
-- Ferramentas para a Documentação:
- **BPMN** – Business Process Model and Notation para modelagem de processos de negócio  
- **UML** – Unified Modeling Language para diagramas de Caso de Uso e Classes  

---

## Estrutura de Dados

O banco de dados foi modelado para suportar as regras de negócio do sistema, contando com as seguintes tabelas principais:

- **Idoso e Cuidador**: Armazenamento de dados pessoais e vínculos de responsabilidade.  
- **Medicamento**: Registro de medicamentos, dosagens, unidades de medida e intervalos de administração.  
- **Meta_Diaria e Registro_Consumo**: Controle personalizado de hidratação e histórico de ingestão.  
- **Alertas**: Controle de status e agendamento de notificações relacionadas a medicamentos e consumo de água.  

---

## Ideia e Conceito do Projeto

A proposta central do projeto é promover a **autonomia e a qualidade de vida da pessoa idosa**.  
O gerenciamento manual de medicamentos e hidratação — frequentemente realizado por meio de anotações em papel ou apenas pela memória — apresenta falhas significativas, podendo resultar em esquecimentos ou dosagens incorretas.

O **VidaFácil** foi concebido para mitigar esses problemas por meio de uma interface acessível, substituindo o controle manual por um sistema organizado de **alertas automáticos** e **registros históricos**, garantindo maior segurança e acompanhamento contínuo.

---

## Funcionalidades do Escopo

As principais funcionalidades previstas para o projeto incluem:

- **Gestão de Medicamentos**: Cadastro completo de medicamentos, incluindo dosagens e horários de administração.  
- **Controle de Hidratação**: Definição de metas diárias personalizadas de consumo de água.  
- **Sistema de Alertas**: Notificações visuais e sonoras para auxiliar o usuário no cumprimento das rotinas.  
- **Histórico de Adesão**: Registro detalhado do consumo de água e medicamentos para fins de acompanhamento.  
- **Vínculo de Cuidadores**: Estrutura de dados preparada para suportar a relação entre paciente e responsável, com possibilidade de interação remota em versões futuras.

---

## Status Atual e Observações

O projeto encontra-se atualmente em fase de **Protótipo Acadêmico**.

- Embora a estrutura de dados (Backend/DB) suporte a interação completa entre **Idoso** e **Cuidador**, a interface desenvolvida neste estágio prioriza exclusivamente a experiência do usuário final (idoso).

---

## Desenvolvedores

Projeto desenvolvido por:

- **Caio Henrique Felix dos Reis Lopes**  
- **Maria Eduarda Ferreira Santos**  
- **Miguel dos Santos Conforte**  
- **Nickolas Aranha Martinez**
