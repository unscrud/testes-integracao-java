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
