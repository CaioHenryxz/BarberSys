# BarberSys - Gestão de Barbearias

![STATUS](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=ORANGE&style=for-the-badge)
![JAVA](http://img.shields.io/static/v1?label=BACKEND&message=JAVA%20SPRING&color=BLUE&style=for-the-badge)

## ✂️ Sobre o Projeto

O **BarberSys** é uma solução completa desenvolvida para modernizar o gerenciamento de barbearias e salões de estética masculina. O sistema visa substituir agendas de papel e planilhas complexas, centralizando o controle de agendamentos, clientes, serviços e fluxo de caixa em uma única plataforma.

O objetivo é otimizar o tempo do profissional e melhorar a experiência do cliente final através de um agendamento rápido e eficiente.

## ⚙️ Funcionalidades Principais

### 📅 Gestão de Agendamentos
- [x] Agendamento de horários por serviço e barbeiro.
- [x] Verificação de conflito de horários (bloqueio de horários ocupados).
- [x] Cancelamento e reagendamento.

### 👥 Controle de Clientes e Profissionais
- [x] Cadastro completo de clientes com histórico de cortes.
- [x] Gerenciamento de equipe (barbeiros) e comissões.

### 💰 Financeiro
- [x] Controle de caixa diário.
- [x] Relatórios de faturamento por período.
- [x] Cadastro de serviços e preços (Corte, Barba, Sobrancelha, etc.).

## 🛠 Tecnologias Utilizadas

O projeto foi desenvolvido seguindo boas práticas de engenharia de software:

**Back-end:**
* **Java 17**
* **Spring Boot** (Web, Data JPA, Validation)
* **Banco de Dados:** MySQL / PostgreSQL
* **Maven** (Gerenciamento de dependências)

**Front-end / Mobile:**
* *(Caso tenha interface web)* **HTML5 / CSS3 / JavaScript**
* *(Caso tenha App)* **Flutter** (Dart)

## 🚀 Como executar o projeto

### Pré-requisitos
* Java JDK 21
* Maven
* Banco de dados configurado (PostgreeSQL)

### Passo a Passo

```bash
# 1. Clone o repositório
git clone [https://github.com/CaioHenryxz/BarberSys.git](https://github.com/CaioHenryxz/BarberSys.git)

# 2. Acesse a pasta do projeto
cd BarberSys

# 3. Configure o banco de dados
# Edite o arquivo src/main/resources/application.properties com suas credenciais

# 4. Compile e execute
./mvnw spring-boot:run
