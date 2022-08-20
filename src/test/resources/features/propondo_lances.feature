# language: pt
Funcionalidade: Propondo lances

Cenario: Propondo um unico lance valido
Dado um lance valido
Quando propoe o lance
Entao o lance eh aceito

Cenario: Propondo varios lances validos
Dado um lance de 10.0 reais do usuario "Ana"
E um lance de 15.0 reais do usuario "Pedro"
Quando propoe os lances
Entao os lances sao aceitos

Esquema do Cenario: Propondo um lance invalido
Dado um lance invalido de <valor> reais do usuario '<nomeUsuario>'
Quando propoe o lance
Entao o lance nao eh aceito

Exemplos:
| valor | nomeUsuario |
| 0.0   | Jonas       |
| -1.0  | Joana       |

Cenario: Propondo uma sequencia de lances
Dado um lance de 10.0 reais do usuario "Zenaldo"
E um lance de 15.0 reais do usuario "Zenaldo"
Quando propoe os lances
Entao o segundo lance nao eh aceito