<?php
session_start();

// Cenas do jogo
$cenas = [
    1 => 'Você acorda desorientado na praia de uma ilha desconhecida. O sol brilha intensamente, e você sente a areia quente sob seus pés enquanto o som suave das ondas ecoa ao seu redor. Seu barco está em pedaços, e você está sozinho.',
    2 => 'Você adentra um vale oculto, onde várias formações de cristal brilham sob a luz do sol. O ambiente é mágico, e você sente uma energia poderosa no ar. Entre os cristais, um em particular chama sua atenção.',
    3 => 'Seguindo uma trilha, você adentra uma floresta densa e misteriosa. O ar é fresco, e o cheiro de terra molhada permeia a neblina que se arrasta pelo chão. As árvores parecem sussurrar segredos, e você sente que está sendo observado.',
    4 => 'Seguindo as pegadas, você chega a um antigo santuário em ruínas. Há inscrições em uma língua desconhecida nas paredes e um pedestal no centro, onde algo parece ter sido retirado.',
    5 => 'Ao abrir o portão de pedra, você encontra uma caverna escura e úmida. Ao fundo, uma serpente gigante guarda um misterioso objeto brilhante.',
    6 => 'No fundo da caverna, você encontra um velho xamã que lhe oferece uma visão do futuro em troca de uma oferta: o cristal especial.',
    7 => 'Agora com a sabedoria adquirida, você volta à caverna para enfrentar a serpente. Sua visão revelou que a flor brilhante pode acalmá-la.',
    8 => 'Com o artefato sagrado em mãos, você retorna ao santuário. Colocá-lo de volta no pedestal ativará o portal para escapar da ilha.'
];

// Inicializa a cena e inventário se não estiverem definidos
if (!isset($_SESSION['scene'])) {
    $_SESSION['scene'] = 1; // Começa na primeira cena
    $_SESSION['inventory'] = []; // Inicializa o inventário vazio
}

$cena_atual = $_SESSION['scene'];
$mensagem = "";

// Função para exibir itens do inventário
function exibirInventario() {
    if (empty($_SESSION['inventory'])) {
        return "Seu inventário está vazio.";
    }
    return "Itens no seu inventário: " . implode(", ", $_SESSION['inventory']) . ".";
}

// Processa os comandos globais (restart, save, load) antes de verificar a cena
if (isset($_POST['command'])) {
    $comando = strtolower(trim($_POST['command']));

    if ($comando == 'restart') {
        $_SESSION['scene'] = 1;
        $_SESSION['inventory'] = [];
        $mensagem = "O jogo foi reiniciado.";
    } elseif ($comando == 'save') {
        $_SESSION['save'] = ['scene' => $_SESSION['scene'], 'inventory' => $_SESSION['inventory']];
        $mensagem = "O jogo foi salvo.";
    } elseif ($comando == 'load') {
        if (isset($_SESSION['save'])) {
            $_SESSION['scene'] = $_SESSION['save']['scene'];
            $_SESSION['inventory'] = $_SESSION['save']['inventory'];
            $mensagem = "O jogo foi carregado.";
        } else {
            $mensagem = "Nenhum jogo salvo encontrado.";
        }
    } else {
        // Processa os comandos das cenas com base na cena atual
        switch ($cena_atual) {
            case 1:
                if ($comando == 'check barco') {
                    if (!in_array('chave', $_SESSION['inventory'])) {
                        $mensagem = "Você inspeciona os destroços do barco e encontra uma CHAVE! Digite 'get chave' para pegá-la.";
                    } else {
                        $mensagem = "Você já encontrou a chave nos destroços do barco.";
                    }
                } elseif ($comando == 'get chave') {
                    if (!in_array('chave', $_SESSION['inventory'])) {
                        $mensagem = "Você pegou a CHAVE.";
                        $_SESSION['inventory'][] = 'chave';
                    } else {
                        $mensagem = "Você já pegou a chave.";
                    }
                } elseif ($comando == 'check baú') {
                    $mensagem = "O baú está trancado. Você precisará de uma chave para abri-lo.";
                } elseif ($comando == 'open baú' && in_array('chave', $_SESSION['inventory'])) {
                    if (!in_array('bússola', $_SESSION['inventory'])) {
                        $mensagem = "Você abriu o baú e encontrou uma BÚSSOLA.";
                        $_SESSION['inventory'][] = 'bússola';
                    } else {
                        $mensagem = "Você já abriu o baú e pegou a bússola.";
                    }
                } elseif ($comando == 'use bússola' && in_array('bússola', $_SESSION['inventory'])) {
                    $mensagem = "Você usa a bússola e percebe que ela aponta para o norte.";
                } elseif ($comando == 'next') {
                    if (!in_array('bússola', $_SESSION['inventory'])) {
                        $mensagem = "Você não pode avançar sem pegar a bússola.";
                    } else {
                        $mensagem = "Você segue em frente para a próxima cena.";
                        $_SESSION['scene'] = 2; 
                    }
                } elseif ($comando == 'help') {
                    $mensagem = "Comandos disponíveis: check barco, get chave, check baú, open baú, use bússola, next.";
                } else {
                    $mensagem = "Comando não reconhecido.";
                }
                break;

            case 2:
                if ($comando == 'check cristal') {
                    $mensagem = "O cristal brilha intensamente, parece diferente dos outros.";
                } elseif ($comando == 'get cristal') {
                    if (!in_array('cristal especial', $_SESSION['inventory'])) {
                        $mensagem = "Você pegou o CRISTAL ESPECIAL.";
                        $_SESSION['inventory'][] = 'cristal especial';
                    } else {
                        $mensagem = "Você já pegou o cristal.";
                    }
                } elseif ($comando == 'next') {
                    if (!in_array('cristal especial', $_SESSION['inventory'])) {
                        $mensagem = "Você não pode avançar sem pegar o cristal especial.";
                    } else {
                        $mensagem = "Você segue em frente para a próxima cena.";
                        $_SESSION['scene'] = 3; 
                    }                    
                } elseif ($comando == 'help') {
                    $mensagem = "Comandos disponíveis: check cristal, get cristal, next.";
                } else {
                    $mensagem = "Comando não reconhecido.";
                }
                break;

            case 3:
                if ($comando == 'check pegadas') {
                    $mensagem = "As pegadas parecem levar para o santuário.";
                } elseif ($comando == 'check flor') {
                    $mensagem = "A flor brilha intensamente na neblina.";
                } elseif ($comando == 'get flor') {
                    if (!in_array('flor brilhante', $_SESSION['inventory'])) {
                        $mensagem = "Você pegou a FLOR BRILHANTE.";
                        $_SESSION['inventory'][] = 'flor brilhante';
                    } else {
                        $mensagem = "Você já pegou a flor.";
                    }
                } elseif ($comando == 'next') {
                    if (!in_array('flor brilhante', $_SESSION['inventory'])) {
                        $mensagem = "Você não pode avançar sem pegar a flor brilhante.";
                    } else {
                        $mensagem = "Você segue em frente para a próxima cena.";
                        $_SESSION['scene'] = 4;
                    }
                } elseif ($comando == 'help') {
                    $mensagem = "Comandos disponíveis: check pegadas, check flor, get flor, next.";
                } else {
                    $mensagem = "Comando não reconhecido.";
                }
                break;

            case 4:
                if ($comando == 'check inscrições') {
                    $mensagem = "As inscrições falam sobre um ritual antigo.";
                } elseif ($comando == 'check pedestal') {
                    $mensagem = "O pedestal está vazio, algo sagrado deveria estar aqui.";
                } elseif ($comando == 'next') {
                    $mensagem = "Você segue em frente para a próxima cena.";
                    $_SESSION['scene'] = 5;
                } elseif ($comando == 'help') {
                    $mensagem = "Comandos disponíveis: check inscrições, check pedestal, next.";
                } else {
                    $mensagem = "Comando não reconhecido.";
                }
                break;

                case 5:
                    if ($comando == 'check serpente') {
                        $mensagem = "A serpente parece estar guardando algo.";
                    } elseif ($comando == 'check artefato') {
                        $mensagem = "O artefato brilha ao longe, mas a serpente está no caminho.";
                    } elseif ($comando == 'next') {
                        if (in_array('flor brilhante', $_SESSION['inventory'])) {
                            $mensagem = "Você avança para a próxima cena após acalmar a serpente.";
                            $_SESSION['scene'] = 6; // Avança para a cena 6 se a flor foi usada
                        } else {
                            $mensagem = "Você não pode avançar sem acalmar a serpente primeiro usando a flor brilhante.";
                        }
                    } elseif ($comando == 'help') {
                        $mensagem = "Comandos disponíveis: check serpente, check artefato, next.";
                    } else {
                        $mensagem = "Comando não reconhecido.";
                    }
                    break;
                                

                    case 6:
                        if ($comando == 'check xamã') {
                            $mensagem = "O xamã olha profundamente em seus olhos e diz que uma oferta é necessária para ver o futuro.";
                        } elseif ($comando == 'offer cristal especial' && in_array('cristal especial', $_SESSION['inventory'])) {
                            $mensagem = "Você oferece o CRISTAL ESPECIAL ao xamã. Ele sorri e, com um gesto de suas mãos, revela uma visão do futuro.";
                            $_SESSION['scene'] = 7; // Avança para a cena 7 após a oferta
                        } elseif ($comando == 'next') {
                            $mensagem = "Você não pode avançar sem oferecer o cristal especial ao xamã.";
                        } elseif ($comando == 'help') {
                            $mensagem = "Comandos disponíveis: check xamã, offer cristal especial, next.";
                        } else {
                            $mensagem = "Comando não reconhecido.";
                        }
                        break;
                    
                        case 7:
                            if ($comando == 'check serpente') {
                                $mensagem = "A serpente parece estar guardando algo. Você precisa usar a flor brilhante para acalmá-la.";
                            } elseif ($comando == 'use flor' && in_array('flor brilhante', $_SESSION['inventory'])) {
                                $mensagem = "Você usa a flor brilhante para acalmar a serpente. Ela se afasta, revelando um artefato sagrado.";
                                $_SESSION['serpente_acalmada'] = true; // Marca que a serpente foi acalmada
                            } elseif ($comando == 'get artefato') {
                                if (!isset($_SESSION['serpente_acalmada']) || $_SESSION['serpente_acalmada'] === false) {
                                    $mensagem = "A serpente ainda está no caminho. Use a flor brilhante para acalmá-la primeiro.";
                                } elseif (!in_array('artefato sagrado', $_SESSION['inventory'])) {
                                    $mensagem = "Você pegou o ARTEFATO SAGRADO.";
                                    $_SESSION['inventory'][] = 'artefato sagrado';
                                } else {
                                    $mensagem = "Você já pegou o artefato sagrado.";
                                }
                            } elseif ($comando == 'next') {
                                if (!in_array('artefato sagrado', $_SESSION['inventory'])) {
                                    $mensagem = "Você não pode avançar sem pegar o artefato sagrado.";
                                } else {
                                    $mensagem = "Você avança para a próxima cena.";
                                    $_SESSION['scene'] = 8; // Avança para a cena 8
                                }
                            } elseif ($comando == 'help') {
                                $mensagem = "Comandos disponíveis: check serpente, use flor, get artefato, next.";
                            } else {
                                $mensagem = "Comando não reconhecido.";
                            }
                            break;
                        
                        
                            case 8:
                                if ($comando == 'use artefato' && in_array('artefato sagrado', $_SESSION['inventory'])) {
                                    $mensagem = "Você coloca o ARTEFATO SAGRADO no pedestal vazio. Uma luz intensa emana do santuário, e o portal começa a brilhar.";
                                    $_SESSION['portal_ativado'] = true; // Marca que o portal foi ativado
                                } elseif ($comando == 'next') {
                                    if (!isset($_SESSION['portal_ativado']) || $_SESSION['portal_ativado'] === false) {
                                        $mensagem = "Você não pode avançar sem ativar o portal usando o artefato sagrado.";
                                    } else {
                                        $mensagem = "Você atravessa o portal brilhante e sente uma leveza indescritível. Parabéns! Você escapou da ilha misteriosa e completou a aventura!";
                                        $_SESSION['scene'] = 9; // Marca que o jogo foi concluído
                                    }
                                } elseif ($comando == 'help') {
                                    $mensagem = "Comandos disponíveis: use artefato, next.";
                                } else {
                                    $mensagem = "Comando não reconhecido.";
                                }
                                break;
                                                   

            // Continue com as próximas cenas...
        }
    }
}
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>O Mistério da Ilha Esquecida</title>
    <style>
        body {
            /* Use o link direto da imagem abaixo */
            background-image: url('https://img.freepik.com/fotos-premium/uma-ilha-misteriosa-com-ruinas-e-segredos-ia-geradora-de-fundo-panoramico_918839-3918.jpg?w=1380');
            background-size: cover;
            background-repeat: no-repeat;
            height: 100vh; 
            font-family: Arial, sans-serif;
            color: #333;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            padding: 20px;
            background: rgba(255, 255, 255, 0.9);
            border-radius: 10px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
            margin-top: 50px;
        }
        h1 {
            text-align: center;
            color: #007bff;
        }
        .message {
            margin: 20px 0;
            font-size: 1.2em;
        }
        .scene {
            margin: 20px 0;
            font-size: 1.1em;
            line-height: 1.5em;
        }
        .inventory {
            margin-top: 20px;
            font-weight: bold;
            font-size: 1.1em;
        }
        input[type="text"] {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        input[type="submit"] {
            padding: 10px 15px;
            background-color: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            font-size: 1em;
            width: 100%;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
        @media (max-width: 600px) {
            .container {
                width: 95%;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>O Mistério da Ilha Esquecida</h1>
        <div class="message"><?= $mensagem ?></div>
        <div class="scene"><?= $cenas[$cena_atual] ?></div>
        <div class="inventory"><?= exibirInventario() ?></div>
        <form method="post">
            <input type="text" name="command" placeholder="Digite seu comando..." required>
            <input type="submit" value="Enviar">
        </form>
    </div>
</body>
</html>
