# **💡API REST COM JAVA E SPRING BOOT**
Essa foi uma API desenvolvida usando a linguagem **JAVA** na versão **17LTS**, para fornecer e receber dados de uma aplicação de cadastro de medicos e paciente. Nesse projeto fiz a utilização do Framework **SpringBoot** na versão **3.0**, um framework bastente conhecido e ultilizado na comunidade JAVA.


| :placard: Vitrine.Dev |     |
| -------------  | --- |
| :sparkles: Nome        | **MediBrasil API**
| :label: Tecnologias | jJava 17, Spring Boot 3, MySQL E +
| :rocket: URL         | https://github.com/IsaqueAmorim/API_MediBrasil
| :fire: Desafio     | Desafio Pessoal de Aprender e cirar em tempo recorde

<!-- Inserir imagem com a #vitrinedev ao final do link -->
![](https://github.com/IsaqueAmorim/API_MediBrasil/blob/main/restapibg.png#vitrinedev)

## Detalhes do projeto

Textos e imagens que descrevam seu projeto, suas conquistas, seus desafios, próximos passos, etc...
## 📦 **Dependências:**
### 🟢 **Spring Boot**:
- Spring Boot DevTools
- Lombook
- Spring Web
- Spring Data JPA
- MySQL Driver
- Bean Validation
----------------------------------------------------------------

## **Lidando com as requisições na URL ``/pacientes``:**
Essa é uma classe controller que lida com as requeisições que chegam em nossa API pela URL ```/pacientes```. Nela temos os metodos que tratam cada tipo de requisição.
````JAVA
@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public void cadastro(@RequestBody @Valid DadosCadastroPaciente dados){
        pacienteRepository.save(new Paciente(dados));
        System.out.println(dados);
    }

    @GetMapping
    public List<DadosListPaciente> list(){
        return pacienteRepository.findAll().stream().map(DadosListPaciente::new).toList();

    }
    @PutMapping
    @Transactional
    public void atualiza(@RequestBody @Valid DadosAtualizaPaciente dados){
       var paciente = pacienteRepository.getReferenceById(dados.id());
        paciente.atualizaDados(dados);
    }
    @DeleteMapping("/{id}")
    @Transactional
    public void deleta(@PathVariable long id){
        System.out.println("Paciente Deletado: " + pacienteRepository.getReferenceById(id).getNome());
        pacienteRepository.deleteById(id);
    }

}
````

>O Controller que lida com as requisições da URL **````/medicos````** é extremamente parecido com o este que foi apresentado, então não há necessidade de coloca-lo.

## **🗂 Operações com o Banco de Dados**

Para estabelecer uma relação com o banco, deveremos criar uma entidade com os atributos referentes as colunas do nosso banco.

Essa entidade deve conter um ID unico usaremos entçao um numero do tipo ``long```. Nesse atributo colocaremos ainda duas anotações:

- ``@Id``: para que o banco consiga identificar que essa é a nossa **Primary Key**.
- ``@GeneratedValue(strategy = GenerationType.IDENTITY)``: para gerar automaticamente os numeros dos nossos id para que sejam auto incrementáveis e não se repitam.

> Usaremos também algumas outras anotações que não vamos entrar em detalhes mas que você pode encontrar na documentação da dependência.

E então nossa entidade ficou assim:

````JAVA
@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String email;

    private String crm;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;


    public Medico(DadosCadastroMedico dados) {

        this.nome = dados.nome();
        this.email = dados.email();
        this.especialidade = dados.especialidade();
        this.crm = dados.crm();
        this.endereco = new Endereco(dados.endereco());

    }

    public void dadosAtualiza(DadosAtualizaMedico dados) {
        if(dados.nome() != null){
            this.nome = dados.nome();
        }
        if(dados.endereco() != null){
            this.endereco.atualiza(dados.endereco());
        }

    }
}

````
Para realizarmos operações no banco de dados, devemos instanciar um repositorio que estende de uma classe JPA que nos fornecerá todos os metodos necessários para as operações.

Criaremos então um interface **ClienteRepository** 
e vamos extender da interface JpaRepository. Nela passaremos dois atibutos, a entidade e o tipo do id.


````JAVA
@Repository
public interface MedicoRepository extends JpaRepository<Medico,Long> {
}

````

Simples assim, nosso repositório está pronto, com pouquíssimas linhas, e para ultilizar basta usar o princípio de injeção de dependências.

> Voltando no código do Controller você encontrará isso:
>````JAVA
>@Autowired
>  private PacienteRepository pacienteRepository;
>````
>É que a injeção foi feita, a anotação ``@Autowired`` passa a responsabilidade de injeçãom para o spring, fazendo assim que ela seja injetada automaticamente.

E para utilizar os métodos basta usar o atributo **pacienteRepository** para acessar os metodos que foram herdados.
