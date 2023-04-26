<?php 

namespace App\Service;

use App\Entity\Commentaire;
use App\Entity\Conducteur;

use Swift_Mailer;
use Swift_Message;
use Doctrine\Persistence\ManagerRegistry;

class CommentMailer
{
    private $mailer;
    private $doctrine;

    public function __construct(Swift_Mailer $mailer, ManagerRegistry $doctrine)
    {
        $this->mailer = $mailer;
        $this->doctrine = $doctrine;
    }

    public function sendCommentNotification(Commentaire $comment)
    {
        $entityManager = $this->doctrine->getManager();
        $conducteur = $entityManager->getRepository(Conducteur::class)->find($comment->getIdConducteur());
        $conducteurEmail = $conducteur->getEmailConducteur();
        
        $email = (new Swift_Message('You have a new comment'))
            ->setFrom('autoxpresstn@gmail.com')
            ->setTo($conducteurEmail)
            ->setBody("You've received a new comment: \n\n".$comment->getContenu());

        $this->mailer->send($email);
    }
}