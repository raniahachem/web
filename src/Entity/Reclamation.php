<?php

namespace App\Entity;
use Symfony\Component\Validator\Constraints as Assert;
use App\Repository\ReclamationRepository;
use Doctrine\DBAL\Types\Types;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints\GreaterThan;
use Symfony\Component\Validator\Constraints\LessThan;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\Annotation\Groups;
use Symfony\Component\Serializer\SerilizerInterface;
use Doctrine\Common\Collections\ArrayCollection;

#[ORM\Entity(repositoryClass: ReclamationRepository::class)]
class Reclamation
{
    #[ORM\Id]
    #[ORM\GeneratedValue]
    #[ORM\Column]
    private ?int $id = null;

    #[ORM\Column(length: 255)]
    #[Groups("Reclamation")]
    private ?string $type = null;

    #[ORM\Column(type: Types::DATE_MUTABLE)]
    #[Groups("Reclamation")]
    #[Assert\GreaterThanOrEqual('today')]
   #[Assert\LessThanOrEqual('+2 days')]
    private ?\DateTimeInterface $date_r = null;

    #[ORM\Column(length: 255)]
    #[Groups("Reclamation")]
    #[Assert\NotBlank(message:"Description reclamation doit etre non vide")]
     #[Assert\Length(min:7,max:10000, minMessage:"Doit etre > 7.", maxMessage:"Doit etre <=10000")]
    private ?string $description = null;


    

    public function getId(): ?int
    {
        return $this->id;
    }



    public function getType(): ?string
    {
        return $this->type;
    }

    public function setType(string $type): self
    {
        $this->type = $type;

        return $this;
    }

    public function getDateR(): ?\DateTimeInterface
    {
        return $this->date_r;
    }

    public function setDateR(\DateTimeInterface $date_r): self
    {
        $this->date_r = $date_r;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }
    public function __toString()
    {
        return (string) $this->getType();
        $this->getDateR();
        $this->getDescription();
        
    }


    public function __construct()
    {
        $this->date_r = new \DateTime('now');
        $this->messages = new ArrayCollection();
    }

   
    
    /*public function getClientName(): ?string
    {
        return $this->id_client->getNomClient();
    }*/




#[ORM\OneToMany(mappedBy: 'id_rec', targetEntity: Message::class)]
private $messages;

#[ORM\ManyToOne(inversedBy: 'reclamations')]
#[ORM\JoinColumn(name: 'id_client_id', referencedColumnName: 'id_client')]
private ?Client $id_client_id = null;


public function getMessages()
{
    return $this->messages;
}

public function addMessage(Message $message)
{
    if (!$this->messages->contains($message)) {
        $this->messages[] = $message;
        $message->setIdRec($this);
    }

    return $this;
}

public function removeMessage(Message $message)
{
    if ($this->messages->contains($message)) {
        $this->messages->removeElement($message);
        if ($message->getIdRec() === $this) {
            $message->setIdRec(null);
        }
    }

    return $this;
}

public function getIdClient(): ?Client
{
    return $this->id_client_id;
}

public function setIdClient(?Client $id_client_id): self
{
    $this->id_client_id = $id_client_id;

    return $this;
}
public function getClientName(): ?string
{
    return $this->id_client_id ? $this->id_client_id->getNomClient() : null;
}

}
