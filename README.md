# password-validator
An password validator API

##### Pré-requisitos
- _docker_ para executar a aplicação https://linuxize.com/post/how-to-install-and-use-docker-on-ubuntu-18-04/
- _mvn_ and _jdk-8_ para executar os testes unitários e de integração https://linuxize.com/post/how-to-install-apache-maven-on-ubuntu-18-04/

**Cobertura de Teste:**
`85% classes, 94% lines covered`
 
`package passwordvalidator class 85%(6/7) method 94%(17/18) line 94%(32/34)`

##### Como executar os testes?
- Vá para a raiz do projeto e execute o comando do maven `mvn test`

##### Como executar essa API?
- 1ª Saia da pasta raiz do projeto e execute o comando `docker build password-validator -t password-validator`
- 2ª Depois disso, inicie o docker com `docker run -p 8080:8080 --network="host" password-validator`    
- 3ª Abra seu browser favorito and vá para esse endereço `localhost:8080/api/v1/password/AbTp9!fok`, a senha a ser testada ficará após a última barra da URL.

##### Qual foi a abordagem para o desenvolvimento?
- O design de arquitetura adotado foi o Clean Architecture para permitir uma separação melhor das responsabilidades das classes no projeto.
- Um Service apenas com um método público chamado _execute_ para rodar todo o pipe de validações da senha, e cada validação com seus métodos privados (padrão Transaction Script).
