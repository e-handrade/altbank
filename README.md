# Bank

Use case

Uma das estruturas mais básicas para um cartão de crédito é a parte de conta e cartões.
Imagine que toda vez que alguém abre uma conta no alt.bank nós precisamos criar uma
conta para esse novo Customer com base em seus dados cadastrais e também
disponibilizar um cartão de crédito físico e virtual para o novo cliente.
O cartão de crédito físico deve ser enviado por nós via transportadora para o cliente com
base no seu endereço. Somente após a chegada do cartão físico, e sua devida validação, é
que o cliente pode solicitar um cartão virtual.
É muito importante que a nossa base de dados seja o mais concisa possível, siga bons
padrões de normalização de dados e seja livre de problemas como dados duplicados.

Requisitos básicos do caso de uso

● Criação da estrutura necessária para o funcionamento do caso de uso;

● Deve ser possível:

	○ Solicitar a reemissão de um cartão físico (com um motivo para isso, como
		perda ou roubo, por exemplo);
	○ Solicitar o cancelamento da conta.

● Nós precisamos ter 2 webhooks:
	○ Resposta da transportadora sobre a entrega do cartão: Quando o cartão
		é entregue, nós precisamos receber o webhook para ter certeza de que a
		validação do usuário realmente deve ocorrer.

■ JSON esperado da transportadora:

{
   "tracking_id" : "tracking id do cartão",
   "delivery_status" : "status da entrega",
   "delivery_date" : "local date time",
   "delivery_return_reason" : "status about any delivery problem",
   "delivery_address" : "delivery address"
}

○ Mudança automática do CVV: O número do CVV do cartão virtual deve
mudar periodicamente para evitarmos fraudes. Essa informação é gerada
pela processadora de cartões.

■ JSON esperado da processadora:

Unset
{
   "account_id" : "processor account id",
   "card_id" : "processor card id",
   "next_cvv" : 123,
   "expiration_date" : "local date time"
}

Requisitos técnicos

● O mapeamento de entidades na API deve ser feito utilizando Hibernate;

● É necessário criar testes unitários para ao menos uma parte das funcionalidades;

● Os Webhooks devem possuir uma API KEY (enviada via header) para cada empresa
a fim de evitar que o endpoint do webhook seja chamado por outro recurso de modo
indevido;

## Swagger

http://localhost:8080/q/swagger-ui/

## Jacoco

http://localhost:63342/altbank/target/site/jacoco/index.html

## GitHub

https://github.com/e-handrade/altbank

## Iniciando o projeto

1 -  
2 - 
3 - 
4 -  


