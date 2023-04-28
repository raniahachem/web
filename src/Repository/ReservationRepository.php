<?php

namespace App\Repository;

use Symfony\Component\HttpFoundation\RequestStack;
use App\Entity\Offre;
use App\Entity\Reservation;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;
use App\Repository\OffreRepository;

use Symfony\Component\Mime\Email;
 
use Symfony\Component\Mailer\MailerInterface;
use App\Service\MailerService;

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

    public function remove(Reservation $entity, bool $flush = false): void
    {
        $this->getEntityManager()->remove($entity);

        if ($flush) {
            $this->getEntityManager()->flush();
        }
    }

    public function saveO(Reservation $entity, MailerInterface $mailer,bool $flush = false): void
    {
        $offreId = $entity->getIdOffre(); // Assuming the ID attribute in Reservation entity for Offre is 'offre'
        $offreRepository = $this->getEntityManager()->getRepository(Offre::class);
        $offre = $offreRepository->findById($offreId);
       

        
        $email = (new Email())
        ->from('nour.benmiled@esprit.tn')
        ->to('ons.hamdi@esprit.tn')
        ->subject('bienvenue dans notre espace client!')
        ->html('<p>  Votre offre a été réservée. </p>');

    $mailer->send($email);
    
    $session = $this->requestStack->getCurrentRequest()->getSession();
    $session->getFlashBag()->add(
        'success',
        '  Votre offre a été résevée.'
       
    );

    
        if ($offre !== null) {
            $this->getEntityManager()->remove($offre); // Mark Offre entity for removal
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
