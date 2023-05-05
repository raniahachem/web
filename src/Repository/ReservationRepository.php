<?php

namespace App\Repository;

use App\Entity\Reservation;
use App\Entity\Offre;

use Symfony\Component\HttpFoundation\RequestStack;

use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

use Symfony\Component\Mime\Email;
 
use Symfony\Component\Mailer\MailerInterface;
use App\Service\MailerService;

use Symfony\Bridge\Twig\Mime\TemplatedEmail;

/**
 * @extends ServiceEntityRepository<Reservation>
 *
 * @method Reservation|null find($id, $lockMode = null, $lockVersion = null)
 * @method Reservation|null findOneBy(array $criteria, array $orderBy = null)
 * @method Reservation[]    findAll()
 * @method Reservation[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ReservationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry, RequestStack $requestStack)
    {
        parent::__construct($registry, Reservation::class);
        $this->requestStack = $requestStack;
    }

    public function save(Reservation $entity, bool $flush = false): void
    {
        $this->getEntityManager()->persist($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function remove(Reservation $entity,MailerInterface $mailer, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        
        $email = (new Email())
        ->from('nour.benmiled@esprit.tn')
        ->to('ons.hamdi@esprit.tn')
        ->subject('Notification dannulation de réservation')
        ->html('<p>  Le client   a annulé sa réservation de votre offre avec lid :'.$entity->getId().' vous pouvez la républier si vous voulez</p>');

    $mailer->send($email);
    
    $session = $this->requestStack->getCurrentRequest()->getSession();
    $session->getFlashBag()->add(
        'success',
        '  Le client a annulé sa réservation de votre offre avec lid.'
       
    );
 
        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }
    
    public function saveO(Reservation $entity, MailerInterface $mailer,  bool $flush = false): void
{
    $offreId = $entity->getIdOffre();
    $offreRepository = $this->getEntityManager()->getRepository(Offre::class);
    
        /*dd($entity->getIdOffre());*/
        $email = (new Email())
        ->from('nour.benmiled@esprit.tn')
        ->to('ons.hamdi@esprit.tn')
        ->subject('bienvenue dans notre espace Admin!')
        ->html('<p>  Votre offre avec l id:'.$entity->getId().'  a été réservée.</p>' );

    $mailer->send($email);
     

            $mailer->send($email);
    
    $session = $this->requestStack->getCurrentRequest()->getSession();
    $session->getFlashBag()->add(
        'success',
        '  Votre offre a été résevée.'
       
    );$offre = $offreRepository->find($offreId);
    
     

    if ($offre !== null) {
        $this->getEntityManager()->remove($offre);
        $this->getEntityManager()->persist($entity);
    }
    
    if ($flush) {
        $this->getEntityManager()->flush();
    }
}


    
//    /**
//     * @return Reservation[] Returns an array of Reservation objects
//     */
//    public function findByExampleField($value): array
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->orderBy('r.id', 'ASC')
//            ->setMaxResults(10)
//            ->getQuery()
//            ->getResult()
//        ;
//    }

//    public function findOneBySomeField($value): ?Reservation
//    {
//        return $this->createQueryBuilder('r')
//            ->andWhere('r.exampleField = :val')
//            ->setParameter('val', $value)
//            ->getQuery()
//            ->getOneOrNullResult()
//        ;
//    }
}
