<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\MessageRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use DateTimeInterface;
use Symfony\Component\Validator\Constraints\GreaterThan;
use Symfony\Component\Validator\Constraints\LessThan;

#[ORM\Entity(repositoryClass: MessageRepository::class)]
class Message
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;


    #[ORM\Column(length: 255)]
    #[Assert\NotBlank(message:"Contenu du message doit etre non vide")]
     #[Assert\Length(min:7,max:10000, minMessage:"Doit etre > 7.", maxMessage:"Doit etre <=10000")]
    private ?string $contenu = null;

    #[ORM\Column(type: Types::DATETIME_IMMUTABLE)]
private ?DateTimeInterface $date_message = null;

    #[ORM\ManyToOne(inversedBy: 'messages')]
    private ?Reclamation $id_rec = null;

    #[ORM\ManyToOne(inversedBy: 'messages')]
    private ?Admin $id_admin = null;

    

    public function getId(): ?int
    {
        return $this->id;
    }

   
    public function getContenu(): ?string
    {
        return $this->contenu;
    }

    public function setContenu(string $contenu): self
    {
        $this->contenu = $contenu;

        return $this;
    }

    public function getDateMessage(): ?DateTimeInterface
{
    return $this->date_message;
}

public function setDateMessage(DateTimeInterface $date_message): self
{
    $this->date_message = $date_message;

    return $this;
}

    

    public function getIdRec(): ?Reclamation
    {
        return $this->id_rec;
    }

    public function setIdRec(?Reclamation $id_rec): self
    {
        $this->id_rec = $id_rec;

        return $this;
    }
    public function __toString()
    {
        return (string) $this->getContenu();
        $this->getDateMessage();
        $this->getIdRec();
        
    }

    public function getIdAdmin(): ?Admin
    {
        return $this->id_admin;
    }

    public function setIdAdmin(?Admin $id_admin): self
    {
        $this->id_admin = $id_admin;

        return $this;
    }
    public function __construct()
{
    $this->date_message = new \DateTimeImmutable();
}


    public function getAdminName(): ?string
    {
        return $this->id_admin->getId();
    }
    public function getRec(): ?string
    {
        return $this->id_rec->getId();
    }
    
}
