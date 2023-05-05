<?php 

namespace App\Service;

use App\Entity\Commentaire;
use App\Entity\Conducteur;
use App\Entity\Reclamation;
use App\Entity\Client;
use App\Entity\Offre;
use App\Entity\Reservation;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Swift_Mailer;
use Swift_Message;
use Doctrine\Persistence\ManagerRegistry;

class CommentMailer
{
    private $mailer;
    private $doctrine;
    private $session;

    public function __construct(Swift_Mailer $mailer, ManagerRegistry $doctrine, SessionInterface $session)
    {
        $this->mailer = $mailer;
        $this->doctrine = $doctrine;
        $this->session = $session;
    }

    public function sendCommentNotification(Commentaire $comment)
    {
        $entityManager = $this->doctrine->getManager();
        $conducteur = $entityManager->getRepository(Conducteur::class)->find($comment->getIdConducteur());
        $conducteurEmail = $conducteur->getEmailConducteur();
        
        $email = (new Swift_Message('You have a new comment'))
            ->setFrom('autoxpresstn@gmail.com')
            ->setTo($conducteurEmail)
            ->setBody(
                "<p>Dear {$conducteur->getPrenomConducteur()},</p>".
                "<p>We would like to inform you that you have received a new comment from Balkiss Hmidi:</p>".
                "<div style='border:1px solid #ccc;color :red; padding:10px;margin-top:10px;'><p>{$comment->getContenu()}</p></div>".
                "<p>Thank you for using our platform.</p>".
                "<p>Best regards,</p>".
                "<p>AutoXpress Team.</p>",
                'text/html'
            );
        $this->mailer->send($email);
    }



    public function sendEmailRec(Reclamation $reclamation)
    {
        $entityManager = $this->doctrine->getManager();
        $client = $entityManager->getRepository(Client::class)->find($reclamation->getIdClient());
        $emailBody = "<p>Dear {$client->getNomClient()},</p>".
            "<p>We would like to inform you that your reclamation of type {$reclamation->getType()} has been received and is being reviewed by our team.</p>".
            "<p>We will get back to you as soon as possible regarding the status of your reclamation.</p>".
            "<p>Thank you for using our platform.</p>".
            "<p>Best regards,</p>".
            "<p>AutoXpress Team.</p>";
    
        $email = (new Swift_Message('New Reclamation'))
            ->setFrom('autoxpresstn@gmail.com')
            ->setTo($client->getEmailClient())
            ->setBody($emailBody, 'text/html');
            if (!$this->mailer->send($email)) {
                // Gestion des erreurs d'envoi de l'e-mail
                $this->session->getFlashBag()->add('danger', 'Une erreur est survenue lors de l\'envoi de l\'e-mail de notification.');
            } else {
                // Succès de l'envoi de l'e-mail
                $this->session->getFlashBag()->add('success', 'Le commentaire a été ajouté avec succès et un e-mail de notification a été envoyé.');
            }
    }

    public function sendEmailOffre(Offre $offre)
    {
        $entityManager = $this->doctrine->getManager();
         $conducteur = $entityManager->getRepository(conducteur::class)->find($offre->getIdConducteur());
        $emailBody = 
            "<p>Votre offre qui a l'identifiant {$offre->getId()}  a été publié avec succès.</p>".
            "<p>Merci pour votre interaction.</p>".
            "<p>Cordialement,</p>".
            "<p>AutoXpress Team.</p>";
    
        $email = (new Swift_Message('New Offre'))
            ->setFrom('autoxpresstn@gmail.com')
            ->setTo($conducteur->getEmailConducteur())
            ->setBody($emailBody, 'text/html');
            if (!$this->mailer->send($email)) {
                // Gestion des erreurs d'envoi de l'e-mail
                $this->session->getFlashBag()->add('danger', 'Une erreur est survenue lors de l\'envoi de l\'e-mail de notification.');
            } else {
                // Succès de l'envoi de l'e-mail
                $this->session->getFlashBag()->add('success', 'Le commentaire a été ajouté avec succès et un e-mail de notification a été envoyé.');
            }
    }



    public function sendEmailOffreremove(Reservation $reservation,)
    {
        $entityManager = $this->doctrine->getManager();
         $conducteur = $entityManager->getRepository(conducteur::class)->find($reservation->getIdConducteur());
        $emailBody = 
            "<p>Le client a annulé sa réservation de votre offre avec l'id  {$reservation->getId()} vous pouvez la républier si vous voulez  .</p>".
            "<p>Merci pour votre interaction.</p>".
            "<p>Cordialement,</p>".
            "<p>AutoXpress Team.</p>";
    
        $email = (new Swift_Message('Notification Offre'))
            ->setFrom('autoxpresstn@gmail.com')
            ->setTo($conducteur->getEmailConducteur())
            ->setBody($emailBody, 'text/html');
            if (!$this->mailer->send($email)) {
                // Gestion des erreurs d'envoi de l'e-mail
                $this->session->getFlashBag()->add('danger', 'Une erreur est survenue lors de l\'envoi de l\'e-mail de notification.');
            } else {
                // Succès de l'envoi de l'e-mail
                $this->session->getFlashBag()->add('success', 'Le commentaire a été ajouté avec succès et un e-mail de notification a été envoyé.');
            }
    }

    
    public function reserverOffre(Reservation $reservation,)
    {
        $entityManager = $this->doctrine->getManager();
         $conducteur = $entityManager->getRepository(conducteur::class)->find($reservation->getIdConducteur());
        $emailBody = 
            "<p>Le client a réservé votre offre avec l'id  {$reservation->getId()}   .</p>".
            "<p>Merci pour votre interaction.</p>".
            "<p>Cordialement,</p>".
            "<p>AutoXpress Team.</p>";
    
        $email = (new Swift_Message('Notification Offre'))
            ->setFrom('autoxpresstn@gmail.com')
            ->setTo($conducteur->getEmailConducteur())
            ->setBody($emailBody, 'text/html');
            if (!$this->mailer->send($email)) {
                // Gestion des erreurs d'envoi de l'e-mail
                $this->session->getFlashBag()->add('danger', 'Une erreur est survenue lors de l\'envoi de l\'e-mail de notification.');
            } else {
                // Succès de l'envoi de l'e-mail
                $this->session->getFlashBag()->add('success', 'Le commentaire a été ajouté avec succès et un e-mail de notification a été envoyé.');
            }
    }

     


    
   






}