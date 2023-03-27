<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Client
 *
 * @ORM\Table(name="client")
 * @ORM\Entity
 */
class Client
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_client", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idClient;

    /**
     * @var int
     *
     * @ORM\Column(name="cin_client", type="integer", nullable=false)
     */
    private $cinClient;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_client", type="string", length=30, nullable=false)
     */
    private $nomClient;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom_client", type="string", length=30, nullable=false)
     */
    private $prenomClient;

    /**
     * @var string
     *
     * @ORM\Column(name="ville_client", type="string", length=30, nullable=false)
     */
    private $villeClient;

    /**
     * @var int
     *
     * @ORM\Column(name="telephone_client", type="integer", nullable=false)
     */
    private $telephoneClient;

    /**
     * @var string
     *
     * @ORM\Column(name="email_client", type="string", length=200, nullable=false)
     */
    private $emailClient;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp_client", type="string", length=30, nullable=false)
     */
    private $mdpClient;


}
