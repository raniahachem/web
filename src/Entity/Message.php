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
    #[ORM\JoinColumn(name: 'id_admin_id', referencedColumnName: 'id_admin')]
    #[Assert\NotBlank(message:"Admin doit etre selectionné")]
    private ?Admin $id_admin_id = null;

    

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
        return $this->id_admin_id;
    }

    public function setIdAdmin(?Admin $id_admin): self
    {
        $this->id_admin_id = $id_admin;

        return $this;
    }
    public function __construct()
{
    $this->date_message = new \DateTimeImmutable();

}


    /*public function getAdminName(): ?string
    {
        return $this->id_admin_id->getId();
    }*/
    public function getAdminName(): ?string
{
    return $this->id_admin_id->getEmailAd();
}
    public function getRec(): ?string
    {
        return $this->id_rec->getId();
    }
    

    /*{% if reclamation.messages|length > 0 %}
                {% set last_message = reclamation.messages|sort((a, b) => b.DateMessage<=> a.DateMessage)|first %}
                <button class="btn btn-success" onclick="window.location.href='{{ path('app_message_showpourclient', {'id': last_message.id}) }}'">Afficher la réponse</button>
            {% else %}
                Pas encore de réponse
            {% endif %}*/

            
            /*{% if reclamation.messages|length > 0 %}
                {% for message in reclamation.messages|sort((a, b) => a.DateMessage<=>b.DateMessage) %}
                    <button class="btn btn-success" onclick="window.location.href='{{ path('app_message_showpourclient', {'id': message.id}) }}'">{{ message.DateMessage|date('d/m/Y H:i:s') }} - Afficher la réponse</button><br>
                {% endfor %}
            {% else %}
                Pas encore de réponse
            {% endif %}*/

            public function getIdAdminId(): ?Admin
            {
                return $this->id_admin_id;
            }

            public function setIdAdminId(?Admin $id_admin_id): self
            {
                $this->id_admin_id = $id_admin_id;

                return $this;
            }
        
}
