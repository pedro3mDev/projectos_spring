# Single Sign On com Spring (Usando o Protocolo JWT)

# O que é?
Single Sign-On (SSO) é um método que permite aos usuários acessar várias aplicações com apenas uma autenticação, melhorando a conveniência e segurança ao eliminar múltiplas entradas de senha.
Neste Projecto uso como exemplo um Sistema de Recursos Humanos que tem como Modulos:
-Segurança
-RH
-Pagamento

# Fluxo
1. O Usuário acessa o Sistema 
2. O Usuário é Redirecionado para a Autenticação(Login)
3. O Serviço de Autentica o Usuário
4. O Usuário usa o Token de Acesso para aceder aos Serviços (RH e Pagamentos)
5. O Serviços  (RH e Pagamentos) validam

# Ferramentas: 
Java 17
Spring Boot (Versão - 3.3.1)





