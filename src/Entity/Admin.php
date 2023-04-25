<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Admin
 *
 * @ORM\Table(name="admin")
 * @ORM\Entity
 */
class Admin
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_admin", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idAdmin;

    /**
     * @var string
     *
     * @ORM\Column(name="email_ad", type="string", length=200, nullable=false)
     */
    private $emailAd;

    /**
     * @var string
     *
     * @ORM\Column(name="mdp_ad", type="string", length=200, nullable=false)
     */
    private $mdpAd;

    public function getIdAdmin(): ?int
    {
        return $this->idAdmin;
    }

    public function getEmailAd(): ?string
    {
        return $this->emailAd;
    }

    public function setEmailAd(string $emailAd): self
    {
        $this->emailAd = $emailAd;

        return $this;
    }

    public function getMdpAd(): ?string
    {
        return $this->mdpAd;
    }

    public function setMdpAd(string $mdpAd): self
    {
        $this->mdpAd = $mdpAd;

        return $this;
    }


}
