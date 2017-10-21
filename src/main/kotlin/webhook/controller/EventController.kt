package webhook.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import webhook.model.Event
import webhook.repository.EventRepository

/**
 * Github Webhook Event Listener
 */
@RestController
class EventController(val eventRepository: EventRepository, val rabbitTemplate: RabbitTemplate, val objectMapper: ObjectMapper, @Value("\${queue.name}") val queueName: String) {

    @GetMapping
    fun getAllEvents() = eventRepository.findAll()

    @GetMapping("{id}")
    fun getEvent(@PathVariable id: String) = eventRepository.findById(id)

    @PostMapping
    fun postEvents(@RequestBody request: String) {
        val event = eventRepository.save(Event(message = request))
        println("Persisted the github event ${event.id} in db")
        rabbitTemplate.convertAndSend(queueName, objectMapper.writeValueAsString(event))
        println("Published the github event ${event.id} to rabbitmq")
    }

}