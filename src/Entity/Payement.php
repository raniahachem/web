<?php

namespace App\Entity;

use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;

/**
 * Payement
 *
 * @ORM\Table(name="payement")
 * @ORM\Entity
 */
class Payement
{
    /**
     * @var int
     *
     * @ORM\Column(name="paymentId", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $paymentid;

    /**
     * @var int
     *
     * @ORM\Column(name="clientId", type="integer", nullable=false)
     */
    private $clientid;

    /**
     * @var string
     *
     * @ORM\Column(name="modePayment", type="string", length=255, nullable=false)
     */
    private $modepayment;

    /**
     * @var float
     *
     * @ORM\Column(name="prixCourse", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixcourse;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="paymentDate", type="date", nullable=false)
     */
    private $paymentdate;

    public function getPaymentid(): ?int
    {
        return $this->paymentid;
    }

    public function getClientid(): ?int
    {
        return $this->clientid;
    }

    public function setClientid(int $clientid): self
    {
        $this->clientid = $clientid;

        return $this;
    }

    public function getModepayment(): ?string
    {
        return $this->modepayment;
    }

    public function setModepayment(string $modepayment): self
    {
        $this->modepayment = $modepayment;

        return $this;
    }

    public function getPrixcourse(): ?float
    {
        return $this->prixcourse;
    }

    public function setPrixcourse(float $prixcourse): self
    {
        $this->prixcourse = $prixcourse;

        return $this;
    }

    public function getPaymentdate(): ?\DateTimeInterface
    {
        return $this->paymentdate;
    }

    public function setPaymentdate(\DateTimeInterface $paymentdate): self
    {
        $this->paymentdate = $paymentdate;

        return $this;
    }


}
