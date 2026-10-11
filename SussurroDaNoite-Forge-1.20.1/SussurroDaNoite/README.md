# Sussurro da Noite — Minecraft Java 1.20.1 / Forge

Mod de terror compacto, sem dependências extras além do Forge.

## Conteúdo
- **Sino da Ruína**: item amaldiçoado que pode ser usado uma vez a cada 45 segundos.
- Ao tocar, emite sons sinistros e tenta invocar zumbis em locais escuros próximos. Na escuridão, tenta chamar até 3; em locais iluminados, até 1.
- **Presságios noturnos**: enquanto estiver escuro durante a noite, há uma chance baixa de ouvir sussurros distantes. Esse evento é apenas atmosférico e não causa dano.
- O sino tem 64 usos e aparece na aba Ferramentas e Utilidades do criativo.

## Requisitos
- Minecraft Java Edition **1.20.1**
- Minecraft Forge **47.3.0** (ou uma versão Forge 47 compatível)
- Java **17**
- Gradle 8.x instalado (se não usar uma cópia do Forge MDK com Gradle Wrapper)

## Compilar
1. Instale um JDK 17 e Gradle 8.x.
2. Extraia a pasta do projeto.
3. Abra um terminal dentro da pasta `SussurroDaNoite`.
4. Execute:
   - Windows: `gradle.bat build` ou `gradle build`
   - Linux/macOS: `gradle build`
5. O arquivo final será criado em `build/libs/sussurronoite-1.0.0.jar`.
6. Coloque esse `.jar` na pasta `mods` do perfil Forge do Minecraft 1.20.1.

## Executar para testar
- `gradle runClient` abre um cliente de desenvolvimento.
- `gradle runServer` inicia um servidor de desenvolvimento.

## Observações
- O projeto usa os mapeamentos oficiais do Minecraft 1.20.1.
- Se o Gradle solicitar downloads na primeira execução, permita o acesso à internet.
- Faça backup dos mundos antes de testar mods novos.
