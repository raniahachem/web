<?php


namespace App\Service;

use Symfony\Component\Mime\Email;
use Symfony\Component\Mailer\MailerInterface;


 
class MailerService
{
     public function __construct(private MailerInterface $mailer ){} //ktebna private MailerInterface $mailer lenna mch fel att mta sendEmail khaterna menech fel controlleur 
    public function sendEmail( $to = 'ons.hamdi@esprit.tn',
                               $content  ='<p>See Twig integration for better HTML integration!</p>' ,
                               $subject = 'Time for Symfony Mailer!'
    ): void
    {
        $email = (new Email())
            ->from('ons.hamdi@esprit.tn')
            ->to($to)
            //->cc('cc@example.com')
            //->bcc('bcc@example.com')
            //->replyTo('fabien@example.com')
            //->priority(Email::PRIORITY_HIGH)
            ->subject($subject)
            //->text('Sending emails is fun again!')
            ->html('<p>See Twig integration for better HTML integration!</p>' );

        $this->mailer->send($email);

        // ...
    }

}