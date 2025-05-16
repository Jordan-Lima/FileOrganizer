
# 📂 FileOrganizer v1

Um organizador de arquivos em Java que ajuda a manter suas pastas limpas, separando os arquivos automaticamente por tipo, extensão ou data.

## 🚀 Funcionalidades

- 🗃️ Move arquivos para subpastas organizadas por tipo (ex: imagens, documentos, vídeos)
- 🔍 Escaneamento de pastas recursivamente (inclui subpastas)
- 🧼 Evita sobrescrita de arquivos com nomes iguais

## 🛠️ Tecnologias

- Java 17+
- Java NIO / File API

## 📦 Como usar

1. Clone o repositório:

```bash
git clone https://github.com/Jordan-Lima/FileOrganizer.git
````

2. Compile o projeto (via terminal ou IDE como IntelliJ):

```bash
javac src/*.java
```

3. Execute o organizador:

```bash
java -cp src FileOrganizer
```

> O programa pode pedir o caminho da pasta que você quer organizar.

## 📁 Exemplo de estrutura após organização:

```
/Seu Usuario
├── Imagens
│   ├── foto1.jpg
│   └── print.png
├── Documentos
│   ├── contrato.pdf
│   └── resumo.docx
└── Vídeos
    └── clipe.mp4
```

## 💡 Futuras melhorias

* Interface gráfica (GUI) com JavaFX ou Swing
* Suporte a configurações via `.json`
* Agendamento automático via cron (Linux) ou agendador de tarefas (Windows)

## 🧑‍💻 Autor

Feito com 💻 por [Jordan Lima](https://github.com/Jordan-Lima)

---

Sinta-se à vontade para contribuir com ideias ou melhorias! 🚀
